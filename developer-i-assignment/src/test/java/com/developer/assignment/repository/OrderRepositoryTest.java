package com.developer.assignment.repository;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.MemberTestBuilder;
import com.developer.assignment.domain.member.model.Email;
import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import com.developer.assignment.global.config.MasterDataSourceConfig;
import com.developer.assignment.repository.master.OrderMasterRepository;
import com.developer.assignment.test.config.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(MasterDataSourceConfig.class)
public class OrderRepositoryTest extends RepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @BeforeEach
    public void tearDown() {
        orderMasterRepository.deleteAll();
    }

    @DisplayName("주문 테이블 : 단일 저장")
    @Test
    public void orderMaster_singleSave() {
        // given
        Member member = MemberTestBuilder.generate(Email.of("single-order.test@order.save.com"));

        // 단일 저장은 중복 발생이 없음
        OrderId orderId = OrderId.generateOrderId();

        Order order = Order.builder()
                .id(orderId)
                .productName("🍔 햄버거")
                .member(member)
                .build();

        // when
        testEntityManager.persist(member);
        testEntityManager.persist(order);

        // then
        assertTrue(orderMasterRepository.findById(orderId).isPresent());
    }

    @DisplayName("주문 테이블 : N 건 저장 및 목록 조회")
    @Test
    public void orderMaster_listSave() {
        // given
        Member member = MemberTestBuilder.generate(Email.of("list-order.test@order.save.com"));

        testEntityManager.persist(member);

        for (int i = 0; i < 10; i++) {
            // N 번 저장시 중복 발생 가능성 있음
            OrderId orderId = OrderId.generateOrderId();
            while (orderMasterRepository.findById(orderId).isPresent()) {
                orderId = OrderId.generateOrderId();
            }

            Order order = Order.builder()
                    .id(orderId)
                    .productName("🍙 주먹밥")
                    .member(member)
                    .build();

            // when
            testEntityManager.persist(order);
        }

        // then
        assertEquals(10, orderMasterRepository.findAll().size());
    }

    @DisplayName("주문 테이블 : 단일 저장 및 삭제")
    @Test
    public void orderMaster_saveDelete() {
        // given
        Member member = MemberTestBuilder.generate(Email.of("delete-order.test@order.save.com"));

        // 단일 저장은 중복 발생이 없음
        OrderId orderId = OrderId.generateOrderId();

        Order order = Order.builder()
                .id(orderId)
                .productName("🍔 햄버거")
                .member(member)
                .build();

        // when
        testEntityManager.persist(member);
        testEntityManager.persist(order);
        assertTrue(orderMasterRepository.findById(orderId).isPresent());

        // then
        orderMasterRepository.delete(order);
        assertThat(orderMasterRepository.findById(orderId)).isEmpty();
    }

}
