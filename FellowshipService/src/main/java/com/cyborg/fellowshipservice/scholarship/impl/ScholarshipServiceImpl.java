package com.cyborg.fellowshipservice.scholarship.impl;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipdataaccess.repository.ScholarshipRepository;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;
import com.cyborg.fellowshipservice.mapper.ScholarshipMapper;
import com.cyborg.fellowshipservice.scholarship.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;
    private final ScholarshipMapper scholarshipMapper;
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
//    @Transactional
    public CreateScholarshipInBulkResponseModel createScholarshipsInBulk(
            List<CreateScholarshipRequestModel> scholarships) {
        // TODO: push each scholarship to the sqs for notification

        List<Scholarship> scholarshipList = scholarships.stream()
                .map(scholarshipMapper::createScholarshipRequestModelToScholarship).toList();

        List<Scholarship> savedScholarships = scholarshipRepository.insert(scholarshipList);
        return CreateScholarshipInBulkResponseModel.builder()
                .documentCreated(savedScholarships.size())
                .build();
    }
}
