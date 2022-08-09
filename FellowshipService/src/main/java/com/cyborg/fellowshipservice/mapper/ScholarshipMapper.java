package com.cyborg.fellowshipservice.mapper;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.response.scholarship.ScholarshipResponseModel;
import org.springframework.stereotype.Component;

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
                .country(createScholarshipRequestModel.getCountry())
                .createdAt(createScholarshipRequestModel.getCreatedAt())
                .build();
    }
}
