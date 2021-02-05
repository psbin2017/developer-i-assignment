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
public class PhoneNumber {

    @ApiModelProperty(name = "전화번호", dataType = "String", value = "숫자, 전화 번호 형식, (암호화)", example = "010-0000-0000")
    @NotNull(message = "전화번호가 비어있습니다.")
    @Size(max = 20, message = "전화번호는 최대 20 자로 입력해주세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])[-]{1}(\\d{3}|\\d{4})[-]{1}(\\d{4})$", message = "전화번호를 정확히 입력해주세요. (ex: 010-0000-0000)")
    @Convert(converter = CryptoStringConverter.class)
    @Column(name = "phone_number", length = 40)
    private String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }
}
