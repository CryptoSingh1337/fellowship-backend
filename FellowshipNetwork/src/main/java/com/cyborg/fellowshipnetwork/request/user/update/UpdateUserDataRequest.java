package com.cyborg.fellowshipnetwork.request.user.update;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class UpdateUserDataRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String country;
    @NotNull
    private Degree degree;
    @NotBlank
    private String programme;
    @NotBlank
    private String branch;
    @NotBlank
    private String category;
    @NotNull
    private Integer income;
    @NotNull
    private Double percentage;
}
