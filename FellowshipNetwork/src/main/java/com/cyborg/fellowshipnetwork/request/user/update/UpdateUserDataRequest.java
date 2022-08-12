package com.cyborg.fellowshipnetwork.request.user.update;

import lombok.Getter;
import lombok.Setter;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class UpdateUserDataRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String program;
}
