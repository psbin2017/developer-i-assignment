package com.developer.assignment.domain.member.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
@Getter
@Embeddable
public class Name implements Serializable { // Session Object Required Serializable

    @ApiModelProperty(name = "이름", dataType = "String", value = "한글, 영문 대소문자만 허용", example = "홍길동")
    @NotNull(message = "이름이 비어있습니다.")
    @Size(min = 1, max = 20, message = "이름은 1 ~ 20 자로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 한글, 영문 대소문자만 입력해주세요.")
    @Column(name = "name", length = 20)
    private String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        return new Name(value);
    }

}
