package com.cyborg.fellowshipnetwork.request.scholarship;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class CreateScholarshipRequestModel {

    private String title;
    private String url;
    private String grant;
    private String description;
    private String deadline;
    private String country;
    private LocalDateTime createdAt;
}
