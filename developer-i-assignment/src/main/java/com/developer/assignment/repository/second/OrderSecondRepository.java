package com.developer.assignment.repository.second;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSecondRepository extends JpaRepository<Order, OrderId> {

    List<Order> findOrdersByMember(Member member);

}
