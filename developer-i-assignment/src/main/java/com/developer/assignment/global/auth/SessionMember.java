package com.developer.assignment.global.auth;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.Name;
import com.developer.assignment.domain.member.model.NickName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMember implements Serializable { // Session Object required Serializable

    private Long id;

    private Name name;

    private NickName nickName;

    @Builder
    public SessionMember(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickName = member.getNickname();
    }

}
