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
    private List<String> programme;
    @NotNull
    private List<String> branch;
    @NotNull
    private List<String> category;
    private String search;
    private Integer income;
    @NotNull
    private Integer page;
}
