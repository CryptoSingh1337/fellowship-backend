package com.cyborg.fellowshipservice.scholarship;

import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.request.scholarship.SearchScholarshipRequest;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipCountriesResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsNoPageResponse;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;

import java.util.List;

/**
 * @author saranshk04
 */
public interface ScholarshipService {

    GetAllScholarshipsNoPageResponse getAllScholarships();

    GetAllScholarshipsResponse getAllScholarships(int page);

    GetAllScholarshipCountriesResponseModel getAllScholarshipCountries();

    CreateScholarshipInBulkResponseModel createScholarshipsInBulk(List<CreateScholarshipRequestModel> scholarships);

    GetAllScholarshipsResponse searchScholarshipByFilter(SearchScholarshipRequest request);
}
