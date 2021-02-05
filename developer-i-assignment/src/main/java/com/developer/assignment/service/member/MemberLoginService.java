package com.developer.assignment.service.member;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.global.auth.SessionMember;
import com.developer.assignment.global.error.exception.CommonException;
import com.developer.assignment.global.web.ResponseCode;
import com.developer.assignment.repository.master.MemberMasterRepository;
import com.developer.assignment.repository.second.MemberSecondRepository;
import com.developer.assignment.web.dto.member.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberLoginService {

    private final MemberMasterRepository memberMasterRepository;

    private final MemberSecondRepository memberSecondRepository;

    private final HttpSession httpSession;

    /**
     * 요청받은 이메일과 패스워드로 로그인합니다.
     * @param loginRequest 로그인 요청 객체
     */
    public void login(LoginRequest loginRequest) {
        Optional<Member> entity = memberSecondRepository.getMemberByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (entity.isPresent()) {
            httpSession.setAttribute("user", new SessionMember(entity.get()));
        } else {
            throw new CommonException(ResponseCode.LOGIN_FAIL);
        }
    }

    /**
     * 로그아웃 합니다. 세션을 제거합니다.
     */
    public void logout() {
        httpSession.invalidate();
    }

}
