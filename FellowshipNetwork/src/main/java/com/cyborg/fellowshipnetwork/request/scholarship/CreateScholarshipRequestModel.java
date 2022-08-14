package com.cyborg.fellowshipnetwork.request.scholarship;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Degree> degrees;
    private LocalDateTime createdAt;
}
