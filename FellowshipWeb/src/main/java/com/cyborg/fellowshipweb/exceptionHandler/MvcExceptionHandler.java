package com.cyborg.fellowshipweb.exceptionHandler;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.exception.ResourceNotExistException;
import com.cyborg.utilities.response.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author saranshk04
 */
@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_VAL_400));
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotExistsException(ResourceNotExistException e) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_CLT_404));
    }
}
