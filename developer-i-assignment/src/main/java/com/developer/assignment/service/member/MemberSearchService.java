package com.developer.assignment.service.member;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.order.Order;
import com.developer.assignment.global.error.exception.CommonException;
import com.developer.assignment.global.web.ResponseCode;
import com.developer.assignment.repository.second.MemberQueryRepository;
import com.developer.assignment.repository.second.MemberSecondRepository;
import com.developer.assignment.repository.second.OrderSecondRepository;
import com.developer.assignment.web.dto.member.request.MemberSearchType;
import com.developer.assignment.web.dto.member.response.MembersAndLastOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberSearchService {

    private final MemberSecondRepository memberSecondRepository;

    private final OrderSecondRepository orderSecondRepository;

    private final MemberQueryRepository memberQueryRepository;

    /**
     * 회원 고유 번호로 회원을 단일 조회합니다.
     * @param id 회원 고유 번호
     * @return 회원
     */
    public Member findMemberById(Long id) {
        return memberSecondRepository.findById(id)
                .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND));
    }

    /**
     * 회원에 대한 주문 목록을 조회합니다.
     * @param id 회원 고유 번호
     * @return 주문 목록
     */
    public List<Order> findOrdersByMember(Long id) {
        Member member = memberSecondRepository.findById(id)
                .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND));
        return orderSecondRepository.findOrdersByMember(member);
    }

    /**
     * 검색 조건에 대한 회원 (+마지막 주문) 페이지네이션 결과를 조회합니다.
     * @param memberSearchType 검색 타입
     * @param keyword 검색 단어
     * @param pageable 페이지네이션
     * @return 페이지네이션 회원 (+마지막 주문) 페이지네이션 결과를 조회합니다.
     */
    public Page<MembersAndLastOrderResponse> pagingFindMembers(MemberSearchType memberSearchType, String keyword, Pageable pageable) {
        return memberQueryRepository.pagingFindMembers(memberSearchType, keyword, pageable);
    }
}
