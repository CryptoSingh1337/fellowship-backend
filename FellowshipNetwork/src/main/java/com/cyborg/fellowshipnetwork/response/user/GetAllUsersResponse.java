package com.cyborg.fellowshipnetwork.response.user;

import com.cyborg.fellowshipdataaccess.entity.Degree;
import com.cyborg.fellowshipdataaccess.entity.Role;
import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

import java.util.Collection;
import java.util.List;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllUsersResponse implements Response {

    List<UserResponse> users;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private String id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private String country;
        private Degree degree;
        private Collection<Role> roles;
    }
}
