package com.developer.assignment.web.dto.member.request;

import com.developer.assignment.domain.member.model.Email;
import com.developer.assignment.domain.member.model.Password;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class LoginRequest {

    @Valid
    private Email email;

    private Password password;
}
