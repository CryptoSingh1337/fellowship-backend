package com.cyborg.fellowshipnetwork.request.scholarship;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author saranshk04
 */
@Getter
@Setter
@ToString
public class SearchScholarshipRequest {

    @NotNull
    private List<Degree> degrees;
    @NotNull
    private List<String> countries;
    @NotNull
    private Integer page;
    private String search;
}
