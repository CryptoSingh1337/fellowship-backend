package com.cyborg.fellowshipweb.exceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.exception.ResourceNotExistException;
import com.cyborg.utilities.exception.UnauthorizedException;
import com.cyborg.utilities.response.ApiResponseUtil;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * @author saranshk04
 */
@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_VAL_400));
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotExistsException(ResourceNotExistException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_CLT_404));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(FORBIDDEN)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_004));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_CLT_404));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<?> handleJwtVerificationException(JWTVerificationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_002));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> handleTokenExpiredException(TokenExpiredException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_003));
    }

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<?> handleDuplicateKeyException(MongoWriteException e) {
        log.error(e.getMessage());
        if (e.getError().getMessage().startsWith("E11000 duplicate key error"))
            return ResponseEntity.status(BAD_REQUEST)
                    .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_ENT_001));
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_INT_500));
    }

    @ExceptionHandler(MongoBulkWriteException.class)
    public ResponseEntity<?> handleDuplicateKeyException(MongoBulkWriteException e) {
        log.error(e.getMessage());
        if (e.getWriteErrors().get(0).getMessage().startsWith("E11000 duplicate key error"))
            return ResponseEntity.status(BAD_REQUEST)
                    .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_ENT_001));
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_INT_500));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<?> handleMailException(MailException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_INT_500));
    }
}
