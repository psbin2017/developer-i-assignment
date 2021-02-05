package com.developer.assignment.web.dto.order.response;

import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class OrderResponse {

    private OrderId id;

    private String productName;

    private LocalDateTime paymentDate;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.productName = order.getProductName();
        this.paymentDate = order.getPaymentDate();
    }
}
