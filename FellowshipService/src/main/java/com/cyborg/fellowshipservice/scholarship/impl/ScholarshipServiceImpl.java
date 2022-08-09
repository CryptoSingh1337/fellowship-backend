package com.cyborg.fellowshipservice.scholarship.impl;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipdataaccess.repository.ScholarshipRepository;
import com.cyborg.fellowshipjms.config.producer.SQSProducer;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
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
import org.springframework.stereotype.Service;

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
    private final SQSProducer sqsProducer;
    private final ScholarshipMapper scholarshipMapper;
    private final NotificationMapper notificationMapper;
    @Value("${scholarships.page.offset}")
    private int PAGE_OFFSET;

    @Override
    public GetAllScholarshipsResponse getAllScholarships(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_OFFSET);
        return GetAllScholarshipsResponse.builder()
                .scholarships(scholarshipRepository.findAll(pageable).stream()
                        .map(scholarshipMapper::scholarshipToScholarshipResponseModel)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
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
}
