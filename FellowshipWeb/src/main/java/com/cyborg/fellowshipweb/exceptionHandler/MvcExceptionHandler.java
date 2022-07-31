package com.cyborg.fellowshipweb.exceptionHandler;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.exception.ResourceNotExistException;
import com.cyborg.utilities.response.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author saranshk04
 */
@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ResourceNotExistException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotExistsException(ResourceNotExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_CNT_404));
    }
}
