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
    private String country;
    @NotNull
    private List<Degree> degrees;
    private LocalDateTime createdAt;
}
