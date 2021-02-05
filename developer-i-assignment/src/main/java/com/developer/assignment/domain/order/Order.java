package com.developer.assignment.domain.order;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.order.model.OrderId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "product_order")
public class Order {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "order_id", nullable = false, length = 12))
    private OrderId id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @CreationTimestamp
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(OrderId id, String productName, Member member) {
        this.id = id;
        this.productName = productName;
        this.member = member;
    }

}
