package com.cyborg.fellowshipnetwork.response.user;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseModel implements Response {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
