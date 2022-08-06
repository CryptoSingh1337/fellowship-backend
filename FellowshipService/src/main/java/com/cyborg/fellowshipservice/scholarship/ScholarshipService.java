package com.cyborg.fellowshipservice.scholarship;

import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;

import java.util.List;

/**
 * @author saranshk04
 */
public interface ScholarshipService {

    GetAllScholarshipsResponse getAllScholarships(int page);
    CreateScholarshipInBulkResponseModel createScholarshipsInBulk(List<CreateScholarshipRequestModel> scholarships);
}