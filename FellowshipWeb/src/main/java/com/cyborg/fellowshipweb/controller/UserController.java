package com.cyborg.fellowshipweb.controller;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.global.Response;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.response.login.RefreshTokenResponseModel;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponseModel;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.jwt.JwtUtils;
import com.cyborg.utilities.response.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Response>> createUser(
            @Validated @RequestBody CreateUserRequestModel createUserRequestModel) {
        UserResponseModel userResponseModel = userService.createUser(createUserRequestModel);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(userResponseModel));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllUsers() {
        GetAllUsersResponse users = userService.getAllUsers();
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(users));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<Response>> getUser(@PathVariable String username) {
        UserResponseModel userResponseModel = userService.getUserResponseModel(username);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(userResponseModel));
    }

    @PostMapping(value = "/token/refresh", produces = {"application/json"})
    public ResponseEntity<?> refreshToken(HttpServletRequest req) {
        String authToken = req.getHeader(AUTHORIZATION);
        String token = jwtUtils.extractAuthorizationToken(authToken);
        if (token != null) {
            JWTVerifier jwtVerifier = jwtUtils.getRefreshTokenVerifier();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String username = decodedJWT.getSubject();
            User user = (User) userService.loadUserByUsername(username);
            RefreshTokenResponseModel refreshTokenResponseModel = jwtUtils.getTokens(user, decodedJWT);
            return ResponseEntity.ok(ApiResponseUtil.createApiSuccessResponse(refreshTokenResponseModel));
        } else {
            return ResponseEntity.status(UNAUTHORIZED)
                    .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_001));
        }
    }
}
