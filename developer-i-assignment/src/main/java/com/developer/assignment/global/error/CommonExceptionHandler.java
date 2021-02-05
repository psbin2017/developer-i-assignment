package com.developer.assignment.global.error;

import com.developer.assignment.global.error.exception.CommonException;
import com.developer.assignment.global.web.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * https://github.com/cheese10yun/spring-guide/blob/master/docs/exception-guide.md
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ResponseCode.INVALID_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ResponseCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 공통 예외 발생
     */
    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<ErrorResponse> handleCommonException(final CommonException e) {
        log.error("handleCommonException", e);
        final ResponseCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * 선언하지 않은 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ResponseCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
