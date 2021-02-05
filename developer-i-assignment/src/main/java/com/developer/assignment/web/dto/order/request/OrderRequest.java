package com.developer.assignment.web.dto.order.request;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class OrderRequest {

    @Valid
    @NotNull(message = "상품명이 비었습니다.")
    @Size(min = 1, max = 100, message = "상품명은 최대 100 자로 입력해주세요.")
    private String productName;

    public OrderRequest(@Valid String productName) {
        this.productName = productName;
    }

    public Order toEntity(OrderId id, Member member) {
        return Order.builder()
                    .id(id)
                    .productName(productName)
                    .member(member)
                    .build();
    }
}
