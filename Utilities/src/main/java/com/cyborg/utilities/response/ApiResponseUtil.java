package com.cyborg.utilities.response;

import com.cyborg.fellowshipnetwork.global.ApiResponse;
import com.cyborg.fellowshipnetwork.global.ErrorResponse;
import com.cyborg.fellowshipnetwork.global.Response;
import com.cyborg.utilities.error.AppErrorCode;

import java.util.List;

import static com.cyborg.fellowshipnetwork.global.ResponseStatus.ERROR;
import static com.cyborg.fellowshipnetwork.global.ResponseStatus.SUCCESS;

/**
 * @author saranshk04
 */
public class ApiResponseUtil {

    private ApiResponseUtil() {
        throw new IllegalArgumentException("Should not be initialized");
    }

    public static <T extends Response> ApiResponse<T> createApiSuccessResponse(T data) {
        return ApiResponse.<T>builder()
                .responseStatus(SUCCESS)
                .data(data)
                .error(null)
                .build();
    }

    public static <T extends Response> ApiResponse<T> createApiErrorResponse(AppErrorCode error) {
        return ApiResponse.<T>builder()
                .responseStatus(ERROR)
                .data(null)
                .error(ErrorResponse.builder()
                        .code(error.getErrorCode())
                        .message(List.of(error.getMessage()))
                        .build())
                .build();
    }
}
