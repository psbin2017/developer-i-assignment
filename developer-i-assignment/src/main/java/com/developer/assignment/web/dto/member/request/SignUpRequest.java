package com.developer.assignment.web.dto.member.request;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.*;
import lombok.*;

import javax.validation.Valid;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class SignUpRequest {

    @Valid
    private Name name;

    @Valid
    private NickName nickName;

    @Valid
    private Password password;

    @Valid
    private PhoneNumber phoneNumber;

    @Valid
    private Email email;

    private Gender gender;

    @Builder
    public SignUpRequest(@Valid Name name, @Valid NickName nickName, @Valid Password password, @Valid PhoneNumber phoneNumber, @Valid Email email, Gender gender) {
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .nickname(nickName)
                .password(password)
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .build();
    }
}
