package com.developer.assignment.domain.member.model;

import com.developer.assignment.global.domain.CryptoStringConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
@Getter
@Embeddable
public class Password {

    @ApiModelProperty(name = "비밀번호", dataType = "String", value = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, (암호화)", example = "abcDEF123!@#")
    @NotNull(message = "비밀번호가 비어있습니다.")
    @Size(min = 10, max = 100, message = "비밀번호는 10 ~ 100 자로 입력해주세요.")
    @Pattern(regexp = "^.*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 숫자, 영문 소문자와 대문자 그리고 특수문자를 1개 이상 포함하여 입력해주세요.")
    @Convert(converter = CryptoStringConverter.class)
    @Column(name = "password", length = 200)
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }

}
