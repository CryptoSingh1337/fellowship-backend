package com.cyborg.fellowshipweb.controller;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.global.Response;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.request.user.update.UpdateUserDataRequest;
import com.cyborg.fellowshipnetwork.response.login.RefreshTokenResponseModel;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UpdateUserDataResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponse;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.jwt.JwtUtils;
import com.cyborg.utilities.response.ApiResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final ObjectMapper mapper;

    @GetMapping(value = "", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> getUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Map<String, UserResponse> res = new HashMap<>(1);
        res.put("user", userService.getUserResponseModel(principal.toString(), principal.toString()));
        String response = "";
        try {
            response = mapper.writeValueAsString(res);
        } catch (JsonProcessingException ignored) {}
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> createUser(
            @Validated @RequestBody CreateUserRequestModel createUserRequestModel) {
        UserResponse userResponse = userService.createUser(createUserRequestModel);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(userResponse));
    }

    @GetMapping(value = "/all", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> getAllUsers() {
        GetAllUsersResponse users = userService.getAllUsers();
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(users));
    }

    @GetMapping(value = "/{username}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> getUser(@PathVariable String username) {
        String accessUsername = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserResponse userResponse = userService.getUserResponseModel(accessUsername, username);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(userResponse));
    }

    @PutMapping(value = "/update", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> updateUserData(
            @Validated @RequestBody UpdateUserDataRequest userDataRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UpdateUserDataResponse response = userService.updateUserData(username, userDataRequest);
        return ResponseEntity.status(OK)
                .body(ApiResponseUtil.createApiSuccessResponse(response));
    }

    @PostMapping(value = "/token/refresh", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ApiResponse<Response>> refreshToken(HttpServletRequest req) {
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
