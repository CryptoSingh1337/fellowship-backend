package com.cyborg.fellowshipnetwork.response.scholarship;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<String> country;
    private List<Degree> degrees;
    private LocalDateTime createdAt;
}
