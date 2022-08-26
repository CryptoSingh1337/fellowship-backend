package com.cyborg.fellowshipweb.controller;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.request.contact.ContactUsMailRequestModel;
import com.cyborg.fellowshipnetwork.response.ContactUsResponse;
import com.cyborg.fellowshipscheduler.mail.MailService;
import com.cyborg.utilities.response.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class ContactUsController {

    private final MailService mailService;

    @PostMapping(value = "/send", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<ContactUsResponse>> sendContactUsMail(
            @Validated @RequestBody ContactUsMailRequestModel mailRequestModel) {
        mailService.sendContactUsMail(mailRequestModel);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(ContactUsResponse.builder()
                        .message("Mail send successfully")
                        .build()));
    }
}
