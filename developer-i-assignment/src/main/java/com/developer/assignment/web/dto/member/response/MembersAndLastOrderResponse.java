package com.developer.assignment.web.dto.member.response;

import com.developer.assignment.domain.member.model.*;
import com.developer.assignment.domain.order.model.OrderId;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter(value = AccessLevel.PRIVATE)
@Getter
public class MembersAndLastOrderResponse {

    private Name name;

    private NickName nickName;

    private PhoneNumber phoneNumber;

    private Email email;

    private Gender gender;

    private OrderId orderId;

    private String productName;

    private LocalDateTime paymentDate;

    @QueryProjection
    public MembersAndLastOrderResponse(Name name, NickName nickName, PhoneNumber phoneNumber, Email email, Gender gender, OrderId orderId, String productName, LocalDateTime paymentDate) {
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.orderId = orderId;
        this.productName = productName;
        this.paymentDate = paymentDate;
    }
}
