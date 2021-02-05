package com.developer.assignment.web.api;

import com.developer.assignment.global.auth.Session;
import com.developer.assignment.global.auth.SessionMember;
import com.developer.assignment.global.web.ApiResult;
import com.developer.assignment.global.web.BasicResponse;
import com.developer.assignment.service.order.OrderService;
import com.developer.assignment.web.dto.order.request.OrderRequest;
import com.developer.assignment.web.dto.order.response.OrderResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class OrderApi {

    private final OrderService orderService;

    @ApiOperation(value = "주문하기", notes = "로그인된 계정으로 주문합니다.")
    @PostMapping("/api/v1/order")
    public BasicResponse order(
            @RequestBody @Valid OrderRequest orderRequest,
            @ApiIgnore @Session SessionMember sessionMember
    ) {
        return ApiResult.success(new OrderResponse(orderService.order(orderRequest, sessionMember)));
    }

}
