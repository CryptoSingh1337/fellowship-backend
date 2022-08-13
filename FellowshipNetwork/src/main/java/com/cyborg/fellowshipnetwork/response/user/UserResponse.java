package com.cyborg.fellowshipnetwork.response.user;

import com.cyborg.fellowshipdataaccess.entity.Role;
import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

import java.util.Collection;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse implements Response {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String country;
    private String program;
    private Collection<Role> roles;
}
