package com.cyborg.fellowshipnetwork.request.user.create;

import lombok.Getter;
import lombok.Setter;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class CreateUserRequestModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
