package com.developer.assignment.global.auth;

import com.developer.assignment.global.config.WebConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * `SessionMember` 를 `@Session` 사용시 Arguments Parameter 로 추가해준다.
 * 단, 핸들러에 등록되어야한다. {@link WebConfig}
 * 설정에 미등록된 경우, 작성된 파라미터는 항상 null 로 반환된다.
 */
@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(Session.class) != null;

        boolean isUserClass = SessionMember.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return httpSession.getAttribute("user");
    }

}
