package com.cyborg.fellowshipservice.mapper;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.response.scholarship.ScholarshipResponseModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author saranshk04
 */
@Component
public class ScholarshipMapper {

    public ScholarshipResponseModel scholarshipToScholarshipResponseModel(Scholarship scholarship) {
        return ScholarshipResponseModel.builder()
                .title(scholarship.getTitle())
                .url(scholarship.getUrl())
                .grant(scholarship.getGrant())
                .description(scholarship.getDescription())
                .deadline(scholarship.getDeadline())
                .country(scholarship.getCountry())
                .programme(scholarship.getProgramme())
                .branch(scholarship.getBranch())
                .category(scholarship.getCategory())
                .degrees(scholarship.getDegrees())
                .income(scholarship.getIncome())
                .merit(scholarship.getMerit())
                .createdAt(scholarship.getCreatedAt())
                .build();
    }

    public Scholarship createScholarshipRequestModelToScholarship(
            CreateScholarshipRequestModel createScholarshipRequestModel) {
        return Scholarship.builder()
                .title(createScholarshipRequestModel.getTitle())
                .url(createScholarshipRequestModel.getUrl())
                .grant(createScholarshipRequestModel.getGrant())
                .description(createScholarshipRequestModel.getDescription())
                .deadline(createScholarshipRequestModel.getDeadline())
                .country(createScholarshipRequestModel.getCountry().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()))
                .programme(createScholarshipRequestModel.getProgramme().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()))
                .branch(createScholarshipRequestModel.getBranch().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()))
                .category(createScholarshipRequestModel.getCategory().stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()))
                .degrees(createScholarshipRequestModel.getDegrees())
                .income(createScholarshipRequestModel.getIncome() == null ? 20_000 :
                        createScholarshipRequestModel.getIncome())
                .merit(createScholarshipRequestModel.getMerit())
                .score(0.0f)
                .createdAt(createScholarshipRequestModel.getCreatedAt())
                .build();
    }
}
