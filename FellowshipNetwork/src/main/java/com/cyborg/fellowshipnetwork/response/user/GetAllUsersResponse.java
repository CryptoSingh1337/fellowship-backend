package com.cyborg.fellowshipnetwork.response.user;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.*;

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

    List<UserResponseModel> users;
}
