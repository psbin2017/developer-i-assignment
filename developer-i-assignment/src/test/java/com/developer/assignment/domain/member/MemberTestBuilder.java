package com.developer.assignment.domain.member;

import com.developer.assignment.domain.member.model.*;

public class MemberTestBuilder {

    private static final String NAME = "PSBIN";
    private static final String NICKNAME = "psbin";
    private static final String PASSWORD = "abcQWE123^&*";
    private static final String PHONE_NUMBER = "010-0000-0000";

    private MemberTestBuilder() {
        throw new AssertionError();
    }

    public static Member generate(Email email) {
        return Member.builder()
                .name(Name.of(NAME))
                .nickname(NickName.of(NICKNAME))
                .password(Password.of(PASSWORD))
                .phoneNumber(PhoneNumber.of(PHONE_NUMBER))
                .email(email)
                .gender(Gender.FEMALE)
                .build();
    }

    public static Member generate(Email email, Name name) {
        return Member.builder()
                .name(Name.of(NAME))
                .nickname(NickName.of(NICKNAME))
                .password(Password.of(PASSWORD))
                .phoneNumber(PhoneNumber.of(PHONE_NUMBER))
                .email(email)
                .gender(Gender.FEMALE)
                .build();
    }

}
