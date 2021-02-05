package com.developer.assignment.global.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 등록일자/수정일자 값을 공통으로 처리하는 추상 클래스이다. 상속하여 사용한다.
 * 스프링 메인 애플리케이션에 `@EnableJpaAuditing` 를 사용해야 동작한다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
