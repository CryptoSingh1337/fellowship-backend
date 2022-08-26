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

    private Degree degree;
    @NotNull
    private List<String> countries;
    private String programme;
    private String branch;
    @NotNull
    private List<String> category;
    private String search;
    @NotNull
    private Integer[] income;
    private Integer percentage;
    @NotNull
    private Integer page;
}
