package com.cyborg.fellowshipnetwork.response.user;

import com.cyborg.fellowshipdataaccess.entity.Degree;
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
public class UpdateUserDataResponse implements Response {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private Degree degree;
}
