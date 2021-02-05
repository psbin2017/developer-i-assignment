package com.developer.assignment.domain.member.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
@Getter
@Embeddable
public class NickName implements Serializable { // Session Object Required Serializable

    @ApiModelProperty(name = "별명", dataType = "String", value = "영문 소문자만 허용", example = "nickname")
    @NotNull(message = "별명이 비어있습니다.")
    @Size(min = 1, max = 30, message = "별명은 1 ~ 30 자로 입력해주세요.")
    @Column(name = "nickname", length = 30)
    private String value;

    private NickName(String value) {
        this.value = value;
    }

    public static NickName of(String value) {
        return new NickName(value);
    }
}
