package com.developer.assignment.web.dto.member.response;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {

    private Name name;

    private NickName nickName;

    private PhoneNumber phoneNumber;

    private Email email;

    private Gender gender;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.nickName = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.gender = member.getGender();
    }
}
