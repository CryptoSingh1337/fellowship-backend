package com.cyborg.fellowshipnetwork.response.scholarship;

import lombok.*;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipResponseModel {

    private String title;
    private String url;
    private String grant;
    private String description;
    private String deadline;
    private String country;
}
