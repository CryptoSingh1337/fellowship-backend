package com.cyborg.fellowshipservice.scholarship.impl;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipdataaccess.repository.ScholarshipRepository;
import com.cyborg.fellowshipjms.config.producer.SQSProducer;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.request.scholarship.SearchScholarshipRequest;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipCountriesResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;
import com.cyborg.fellowshipservice.mapper.NotificationMapper;
import com.cyborg.fellowshipservice.mapper.ScholarshipMapper;
import com.cyborg.fellowshipservice.scholarship.ScholarshipService;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;
    private final MongoTemplate mongoTemplate;
    private final SQSProducer sqsProducer;
    private final ScholarshipMapper scholarshipMapper;
    private final NotificationMapper notificationMapper;
    @Value("${scholarships.page.offset}")
    private int PAGE_OFFSET;

    @Override
    public GetAllScholarshipsResponse getAllScholarships(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_OFFSET, Sort.by("createdAt").descending());
        return GetAllScholarshipsResponse.builder()
                .scholarships(scholarshipRepository.findAll(pageable).stream()
                        .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public GetAllScholarshipCountriesResponseModel getAllScholarshipCountries() {
        List<String> countries = mongoTemplate
                .findDistinct("country", Scholarship.class, String.class);
        return GetAllScholarshipCountriesResponseModel.builder()
                .countries(countries)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateScholarshipInBulkResponseModel createScholarshipsInBulk(
            List<CreateScholarshipRequestModel> scholarships) {
        log.info("In createScholarshipsInBulk");
        List<Scholarship> scholarshipList = scholarships.stream()
                .map(scholarshipMapper::createScholarshipRequestModelToScholarship).toList();

        List<Scholarship> savedScholarships = new ArrayList<>();
        for (Scholarship scholarship : scholarshipList) {
            try {
                if (scholarship.getCreatedAt() == null)
                    scholarship.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
                savedScholarships.add(scholarshipRepository.insert(scholarship));
            } catch (MongoWriteException ignored) {
            }
        }

        for (Scholarship scholarship : scholarshipList)
            sqsProducer.publishToNotificationQueue(notificationMapper
                    .scholarshipToNotificationPayload(scholarship));

        return CreateScholarshipInBulkResponseModel.builder()
                .documentCreated(savedScholarships.size())
                .build();
    }

    @Override
    public GetAllScholarshipsResponse searchScholarshipByTitleAndDescription(SearchScholarshipRequest request) {

        List<Degree> degrees = request.getDegrees();
        List<String> countries = request.getCountries();
        String searchQuery = request.getSearch();
        Integer page = request.getPage();

        countries = countries.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, PAGE_OFFSET);
        Query query = new Query()
                .with(pageable);

        if (degrees.isEmpty() && countries.isEmpty() && !StringUtils.hasText(searchQuery)) {
            return GetAllScholarshipsResponse.builder()
                    .scholarships(scholarshipRepository.findAll(pageable).stream()
                            .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                            .collect(Collectors.toList()))
                    .build();
        }


        if (!degrees.isEmpty())
            query.addCriteria(Criteria.where("degrees")
                    .in(degrees));

        if (!countries.isEmpty())
            query.addCriteria(Criteria.where("country")
                    .in(countries));

        if (StringUtils.hasText(searchQuery))
            query.addCriteria(TextCriteria.forLanguage("en")
                    .caseSensitive(false)
                    .matchingPhrase(searchQuery));

        List<Scholarship> scholarships = mongoTemplate.find(query, Scholarship.class);

        return GetAllScholarshipsResponse.builder()
                .scholarships(scholarships.stream()
                        .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
