package com.developer.assignment.global.error.exception;

import com.developer.assignment.global.web.ResponseCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private ResponseCode errorCode;

    public CommonException(ResponseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CommonException(String message, ResponseCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
