package com.developer.assignment.service.member;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.Email;
import com.developer.assignment.global.auth.SessionMember;
import com.developer.assignment.global.error.exception.CommonException;
import com.developer.assignment.global.web.ResponseCode;
import com.developer.assignment.repository.master.MemberMasterRepository;
import com.developer.assignment.web.dto.member.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberSignUpService {

    private final MemberMasterRepository memberMasterRepository;

    private final HttpSession httpSession;

    /**
     * 회원 가입합니다. 단, 이미 가입된 이메일은 가입할 수 없습니다.
     * @param signUpRequest 회원 가입 요청 객체
     * @return 회원
     */
    public Member signUp(SignUpRequest signUpRequest) {

        if (isAlreadySignInEmail(signUpRequest.getEmail())) {
            throw new CommonException(ResponseCode.DUPLICATE_EMAIL);
        }

        return memberMasterRepository.save(signUpRequest.toEntity());
    }

    /**
     * 로그인한 회원을 탈퇴합니다.
     * @param sessionMember 로그인 회원 세션 객체
     */
    public void signOut(SessionMember sessionMember) {
        // check session
        if (sessionMember == null) {
            throw new CommonException(ResponseCode.ACCESS_DENY);
        }

        Member member = memberMasterRepository.findById(sessionMember.getId())
                                .orElseThrow(() -> new CommonException(ResponseCode.INVALID_VALUE));

        // 주문 테이블도 같이 삭제된다.
        memberMasterRepository.delete(member);

        // 세션 제거
        httpSession.invalidate();
    }

    private boolean isAlreadySignInEmail(Email email) {
        return memberMasterRepository.findByEmail(email).isPresent();
    }

}
