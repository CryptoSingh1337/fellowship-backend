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
import com.cyborg.fellowshipnetwork.response.scholarship.ScholarshipResponseModel;
import com.cyborg.fellowshipservice.mapper.NotificationMapper;
import com.cyborg.fellowshipservice.mapper.ScholarshipMapper;
import com.cyborg.fellowshipservice.scholarship.ScholarshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.support.PageableExecutionUtils;
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
                .scholarships(getConvertedScholarshipPage(pageable))
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
                Scholarship savedScholarship = scholarshipRepository.insert(scholarship);
                savedScholarships.add(savedScholarship);
            } catch (Exception e) {
                log.info("Exception: {}", e.getMessage());
            }
        }

        for (Scholarship scholarship : savedScholarships)
            sqsProducer.publishToNotificationQueue(notificationMapper
                    .scholarshipToNotificationPayload(scholarship));

        return CreateScholarshipInBulkResponseModel.builder()
                .documentCreated(savedScholarships.size())
                .build();
    }

    @Override
    public GetAllScholarshipsResponse searchScholarshipByFilter(SearchScholarshipRequest request) {

        Degree degree = request.getDegree();
        List<String> countries = request.getCountries();
        String programme = request.getProgramme();
        String branch = request.getBranch();
        List<String> category = request.getCategory();
        String searchQuery = request.getSearch();
        Integer[] income = request.getIncome();
        Integer page = request.getPage();

        countries = countries.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, PAGE_OFFSET)
                .withSort(Sort.by("createdAt").descending());
        Query query = new Query()
                .with(pageable)
                .skip(Integer.toUnsignedLong(pageable.getPageSize() * pageable.getPageNumber()))
                .limit(pageable.getPageSize());

        if (degree == null && countries.isEmpty() && !StringUtils.hasText(programme) &&
                !StringUtils.hasText(branch) && category.isEmpty() && income.length == 0 &&
                !StringUtils.hasText(searchQuery)) {
            return GetAllScholarshipsResponse.builder()
                    .scholarships(getConvertedScholarshipPage(pageable))
                    .build();
        }

        if (degree != null)
            query.addCriteria(Criteria.where("degree")
                    .in(degree));

        if (!countries.isEmpty()) {
            countries = countries.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            query.addCriteria(Criteria.where("country")
                    .in(countries));
        }

        if (StringUtils.hasText(programme)) {
            programme = programme.toLowerCase();
            query.addCriteria(Criteria.where("programme")
                    .in(programme));
        }

        if (StringUtils.hasText(branch)) {
            branch = branch.toLowerCase();
            query.addCriteria(Criteria.where("branch")
                    .in(branch));
        }

        if (!category.isEmpty()) {
            category = category.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            query.addCriteria(Criteria.where("category")
                    .in(category));
        }

        if (income != null && income.length == 2) {
            query.addCriteria(Criteria.where("income")
                    .gte(income[0])
                    .lte(income[1]));
        }

        if (StringUtils.hasText(searchQuery)) {
            query.addCriteria(TextCriteria.forLanguage("en")
                    .caseSensitive(false)
                    .matchingPhrase(searchQuery));
        }

        List<ScholarshipResponseModel> scholarships = mongoTemplate.find(query, Scholarship.class).stream()
                .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                .collect(Collectors.toList());

        System.out.println(mongoTemplate.count(query, Scholarship.class));

        return GetAllScholarshipsResponse.builder()
                .scholarships(PageableExecutionUtils.getPage(scholarships, pageable,
                        () -> mongoTemplate.count(query.skip(-1).limit(-1),
                                Scholarship.class)))
                .build();
    }

    private Page<ScholarshipResponseModel> getConvertedScholarshipPage(Pageable pageable) {
        Page<Scholarship> scholarshipPage = scholarshipRepository.findAll(pageable);
        List<ScholarshipResponseModel> scholarshipResponseModels = scholarshipPage.getContent().stream()
                .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                .toList();
        return PageableExecutionUtils.getPage(scholarshipResponseModels, pageable,
                scholarshipPage::getTotalElements);
    }
}
