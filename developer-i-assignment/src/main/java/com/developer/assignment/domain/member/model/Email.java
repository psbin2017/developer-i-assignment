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
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
@Getter
@Embeddable
public class Email {

    @ApiModelProperty(name = "이메일", dataType = "String", value = "이메일 형식, (암호화)", example = "devpsbin2017@github.com")
    @NotNull(message = "이메일이 비어있습니다.")
    @Size(max = 100, message = "이메일은 최대 100 자로 입력해주세요.")
    @javax.validation.constraints.Email(message = "이메일 형식으로 입력해주세요.")
    @Convert(converter = CryptoStringConverter.class)
    @Column(name = "email", length = 200)
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

}
