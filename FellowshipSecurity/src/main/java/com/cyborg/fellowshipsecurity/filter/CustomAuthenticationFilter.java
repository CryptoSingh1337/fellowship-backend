package com.cyborg.fellowshipsecurity.filter;

import com.cyborg.fellowshipnetwork.request.login.LoginRequestModel;
import com.cyborg.fellowshipnetwork.response.login.SuccessfulLoginResponseModel;
import com.cyborg.utilities.jwt.JwtUtils;
import com.cyborg.utilities.response.ApiResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final ObjectMapper mapper;
    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            LoginRequestModel user = mapper.readValue(req.getReader(), LoginRequestModel.class);
            log.debug("Username: {} and Password: {}", user.getUsername(), user.getPassword());
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication authResult)
            throws IOException {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);

        SuccessfulLoginResponseModel successfulLogin = new SuccessfulLoginResponseModel();
        successfulLogin.setAccessToken(accessToken);
        successfulLogin.setRefreshToken(refreshToken);

        res.setContentType("application/json");
        mapper.writeValue(res.getWriter(), ApiResponseUtil.createApiSuccessResponse(successfulLogin));
    }
}
