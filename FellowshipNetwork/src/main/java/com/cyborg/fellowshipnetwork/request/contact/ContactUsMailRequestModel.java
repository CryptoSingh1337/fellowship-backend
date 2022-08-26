package com.cyborg.fellowshipnetwork.request.contact;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactUsMailRequestModel {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String message;
}
