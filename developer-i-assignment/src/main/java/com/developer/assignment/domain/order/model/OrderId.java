package com.developer.assignment.domain.order.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
@Getter
@Embeddable
@EqualsAndHashCode
public class OrderId implements Serializable { // @Id required Serializable

    @Column(name = "order_id", length = 12)
    private String value;

    private OrderId(String value) {
        this.value = value;
    }

    public static OrderId generateOrderId() {
        return new OrderId(generate());
    }

    /**
     * 무작위 영문 대문자와 숫자로 이루어진 12자리 주문 번호를 생성합니다.
     * 단, 생성시 중복 확인을 위해 생성한 아이디로 검색 후 등록해야 한다.
     * TODO 체크 없이 등록하는 방법 고려하기
     * @return 12자리 (ex: 6762W99PU87U, 6B01Q4182FQE)
     */
    private static String generate() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            if (random.nextBoolean()) {
                // 영문 대문자 아스키 65 ~ 90
                stringBuilder.append((char) ((random.nextInt(26)) + 65));
            } else {
                stringBuilder.append((random.nextInt(10)));
            }
        }

        return stringBuilder.toString();
    }

}
