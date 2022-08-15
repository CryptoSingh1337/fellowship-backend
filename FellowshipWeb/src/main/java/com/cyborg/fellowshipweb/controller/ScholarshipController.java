package com.cyborg.fellowshipweb.controller;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.global.Response;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.request.scholarship.SearchScholarshipRequest;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipCountriesResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;
import com.cyborg.fellowshipservice.scholarship.ScholarshipService;
import com.cyborg.utilities.response.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/scholarship")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> getAllScholarships(@RequestParam Integer page) {
        GetAllScholarshipsResponse scholarshipsResponse = scholarshipService.getAllScholarships(page);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(scholarshipsResponse));
    }

    @GetMapping(value = "/get/countries", produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> getAllScholarshipCountries() {
        GetAllScholarshipCountriesResponseModel countriesResponseModel = scholarshipService
                .getAllScholarshipCountries();
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(countriesResponseModel));
    }

    @PostMapping(value = "/get", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> getAllScholarshipsByFilter(
            @Validated @RequestBody SearchScholarshipRequest request) {
        GetAllScholarshipsResponse scholarshipsResponse = scholarshipService
                .searchScholarshipByTitleAndDescription(request);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(scholarshipsResponse));
    }

    @PostMapping(value = "/create/bulk", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> createScholarshipsInBulk(
            @Validated @RequestBody List<CreateScholarshipRequestModel> scholarships) {
        CreateScholarshipInBulkResponseModel createScholarshipInBulkResponseModel =
                scholarshipService.createScholarshipsInBulk(scholarships);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(createScholarshipInBulkResponseModel));
    }
}
