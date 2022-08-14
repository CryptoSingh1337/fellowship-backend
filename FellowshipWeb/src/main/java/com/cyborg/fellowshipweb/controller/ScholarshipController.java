package com.cyborg.fellowshipweb.controller;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.global.Response;
import com.cyborg.fellowshipnetwork.request.scholarship.CreateScholarshipRequestModel;
import com.cyborg.fellowshipnetwork.request.scholarship.SearchScholarshipRequest;
import com.cyborg.fellowshipnetwork.response.scholarship.CreateScholarshipInBulkResponseModel;
import com.cyborg.fellowshipnetwork.response.scholarship.GetAllScholarshipsResponse;
import com.cyborg.fellowshipservice.scholarship.ScholarshipService;
import com.cyborg.utilities.response.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/scholarship")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Response>> getAllScholarships(@RequestParam Integer page) {
        GetAllScholarshipsResponse scholarshipsResponse = scholarshipService.getAllScholarships(page);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(scholarshipsResponse));
    }

    @PostMapping("/get")
    public ResponseEntity<ApiResponse<Response>> getAllScholarshipsByFilter(
            @Validated @RequestBody SearchScholarshipRequest request) {
        log.info("Search request: {}", request);
        GetAllScholarshipsResponse scholarshipsResponse = scholarshipService
                .searchScholarshipByTitleAndDescription(request);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(scholarshipsResponse));
    }

    @PostMapping("/create/bulk")
    public ResponseEntity<ApiResponse<Response>> createScholarshipsInBulk(
            @Validated @RequestBody List<CreateScholarshipRequestModel> scholarships) {
        CreateScholarshipInBulkResponseModel createScholarshipInBulkResponseModel =
                scholarshipService.createScholarshipsInBulk(scholarships);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(createScholarshipInBulkResponseModel));
    }
}
