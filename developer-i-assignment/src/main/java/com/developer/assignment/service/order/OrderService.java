package com.developer.assignment.service.order;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import com.developer.assignment.global.auth.SessionMember;
import com.developer.assignment.global.error.exception.CommonException;
import com.developer.assignment.global.web.ResponseCode;
import com.developer.assignment.repository.master.OrderMasterRepository;
import com.developer.assignment.service.member.MemberSearchService;
import com.developer.assignment.web.dto.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final MemberSearchService memberSearchService;

    private final OrderMasterRepository orderMasterRepository;

    /**
     * 로그인된 회원으로 주문합니다.
     * @param orderRequest 주문 요청 객체
     * @param sessionMember 세션 회원 객체
     * @return 주문
     */
    public Order order(OrderRequest orderRequest, SessionMember sessionMember) {

        // check session
        if (sessionMember == null) {
            throw new CommonException(ResponseCode.ACCESS_DENY);
        }

        // check OrderId duplicate
        OrderId id = OrderId.generateOrderId();
        while (orderMasterRepository.findById(id).isPresent()) {
            id = OrderId.generateOrderId();
        }

        // find member
        Member member = memberSearchService.findMemberById(sessionMember.getId());

        Order entity = orderRequest.toEntity(id, member);
        return orderMasterRepository.save(entity);
    }
}
