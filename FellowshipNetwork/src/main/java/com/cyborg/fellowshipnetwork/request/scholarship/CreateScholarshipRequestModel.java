package com.cyborg.fellowshipnetwork.request.scholarship;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class CreateScholarshipRequestModel {

    @NotBlank
    private String title;
    @NotBlank
    private String url;
    @NotBlank
    private String grant;
    @NotBlank
    private String description;
    @NotBlank
    private String deadline;
    @NotBlank
    private List<String> country;
    @NotBlank
    private List<String> programme;
    @NotBlank
    private List<String> branch;
    @NotBlank
    private List<String> category;
    @NotNull
    private List<Degree> degrees;
    @NotNull
    private Integer income;
    // TODO: to be remove in prod
    private LocalDateTime createdAt;
}
