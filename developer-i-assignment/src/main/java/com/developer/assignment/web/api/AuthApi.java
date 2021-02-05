package com.developer.assignment.web.api;

import com.developer.assignment.global.web.ApiResult;
import com.developer.assignment.global.web.BasicResponse;
import com.developer.assignment.service.member.MemberLoginService;
import com.developer.assignment.service.member.MemberSignUpService;
import com.developer.assignment.web.dto.member.request.LoginRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthApi {

    private final MemberSignUpService memberSignUpService;

    private final MemberLoginService memberLoginService;

    @ApiOperation(value = "회원 로그인(인증)", notes = "등록한 회원으로 로그인합니다.")
    @PostMapping("/api/v1/auth/login")
    public BasicResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        memberLoginService.login(loginRequest);
        return ApiResult.success();
    }

    @ApiOperation(value = "회원 로그아웃", notes = "로그인된 회원을 로그아웃 합니다.")
    @PostMapping("/api/v1/auth/logout")
    public BasicResponse logout() {
        memberLoginService.logout();
        return ApiResult.success();
    }

}
