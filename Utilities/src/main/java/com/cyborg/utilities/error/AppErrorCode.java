package com.cyborg.utilities.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author saranshk04
 */
@Getter
public enum AppErrorCode {

    APP_CLT_404("Resource not exists", "400-001", HttpStatus.BAD_REQUEST),

    APP_VAL_400("Invalid request parameters", "400-002", HttpStatus.BAD_REQUEST),
    APP_AUTH_001("Missing Authorization header", "401-001", HttpStatus.UNAUTHORIZED),
    APP_INT_500("Internal server error", "500-001", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final String errorCode;
    private final HttpStatus status;

    AppErrorCode(String message) {
        this(message, null, null);
    }

    AppErrorCode(String message, HttpStatus httpStatus) {
        this(message, null, httpStatus);
    }

    AppErrorCode(String message, String errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
    }
    public static AppErrorCode fromCode(String errorCode) {
        for (AppErrorCode error : AppErrorCode.values())
            if (error.errorCode.equalsIgnoreCase(errorCode))
                return error;
        return null;
    }
}
