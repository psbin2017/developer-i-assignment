package com.developer.assignment.web.dto;

import com.developer.assignment.domain.member.model.*;
import com.developer.assignment.web.dto.member.request.SignUpRequest;

public class SignUpRequestTestBuilder {

    private static final String VALID_NAME = "한글ABcd";
    private static final String VALID_NICKNAME = "nickname";
    private static final String VALID_PASSWORD = "abcDEF123!@#";
    private static final String VALID_PHONE_NUMBER = "010-0000-0000";
    private static final String VALID_EMAIL = "devpsbin5000@github.com";

    private SignUpRequestTestBuilder() {
        throw new AssertionError();
    }

    public static SignUpRequest name(Name name) {
        return SignUpRequest.builder()
                .name(name)
                .nickName(NickName.of(VALID_NICKNAME))
                .password(Password.of(VALID_PASSWORD))
                .phoneNumber(PhoneNumber.of(VALID_PHONE_NUMBER))
                .email(Email.of(VALID_EMAIL))
                .build();
    }

    public static SignUpRequest nickName(NickName nickName) {
        return SignUpRequest.builder()
                .name(Name.of(VALID_NAME))
                .nickName(nickName)
                .password(Password.of(VALID_PASSWORD))
                .phoneNumber(PhoneNumber.of(VALID_PHONE_NUMBER))
                .email(Email.of(VALID_EMAIL))
                .build();
    }

    public static SignUpRequest password(Password password) {
        return SignUpRequest.builder()
                .name(Name.of(VALID_NAME))
                .nickName(NickName.of(VALID_NICKNAME))
                .password(password)
                .phoneNumber(PhoneNumber.of(VALID_PHONE_NUMBER))
                .email(Email.of(VALID_EMAIL))
                .build();
    }

    public static SignUpRequest phoneNumber(PhoneNumber phoneNumber) {
        return SignUpRequest.builder()
                .name(Name.of(VALID_NAME))
                .nickName(NickName.of(VALID_NICKNAME))
                .password(Password.of(VALID_PASSWORD))
                .phoneNumber(phoneNumber)
                .email(Email.of(VALID_EMAIL))
                .build();
    }

    public static SignUpRequest email(Email email) {
        return SignUpRequest.builder()
                .name(Name.of(VALID_NAME))
                .nickName(NickName.of(VALID_NICKNAME))
                .password(Password.of(VALID_PASSWORD))
                .phoneNumber(PhoneNumber.of(VALID_PHONE_NUMBER))
                .email(email)
                .build();
    }
}
