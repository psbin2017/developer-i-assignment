package com.developer.assignment.global.web;

import lombok.Getter;

/**
 * https://github.com/cheese10yun/spring-guide/blob/master/docs/exception-guide.md
 */
@Getter
public enum ResponseCode {

    // COMMON
    OK("0", "성공하였습니다.", 200),
    INVALID_VALUE("C10001", "요청 값이 잘못되었습니다.", 400),
    INVALID_TYPE_VALUE("C10002", "요청 타입을 지원하지 않습니다.", 400),
    INTERNAL_SERVER_ERROR("C10003", "서버 에러가 발생하였습니다.", 500),
    NOT_FOUND( "C10004", "찾을 수 없습니다.", 400),
    ACCESS_DENY( "C10005", "접근할 수 없습니다.", 400),
    METHOD_NOT_ALLOWED( "C10006", "요청이 잘못 되었습니다.", 405 ),

    // MEMBER
    DUPLICATE_EMAIL( "M10001", "이미 가입된 이메일입니다.", 400),
    LOGIN_FAIL( "M10002", "이메일, 패스워드가 일치하지 않습니다.", 400);

    private final String code;
    private final String message;
    private final int status;

    ResponseCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
