package com.developer.assignment.global.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult extends BasicResponse {
    private String code;
    private String message;
    private Object data;

    private ApiResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ApiResult(Object data) {
        this(ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), data);
    }

    public static ApiResult success() {
        return new ApiResult(null);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(data);
    }

    public static ApiResult successAndMessage(String message, Object data) {
        return new ApiResult(ResponseCode.OK.getCode(), message, data);
    }
}
