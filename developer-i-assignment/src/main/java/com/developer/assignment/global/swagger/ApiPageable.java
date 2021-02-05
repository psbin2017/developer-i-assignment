package com.developer.assignment.global.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "페이지 번호 (0..N)", example = "0"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "페이지 사이즈", example = "10"),
        @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query", value = "정렬 방법", allowMultiple = true)
})
public @interface ApiPageable {
}
