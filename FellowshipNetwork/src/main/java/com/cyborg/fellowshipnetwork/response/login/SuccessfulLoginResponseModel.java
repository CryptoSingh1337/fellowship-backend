package com.cyborg.fellowshipnetwork.response.login;

import com.cyborg.fellowshipnetwork.global.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class SuccessfulLoginResponseModel implements Response {

    private String accessToken;
    private String refreshToken;
}
