package com.developer.assignment.web.api;

import com.developer.assignment.global.auth.Session;
import com.developer.assignment.global.auth.SessionMember;
import com.developer.assignment.global.swagger.ApiPageable;
import com.developer.assignment.global.web.ApiResult;
import com.developer.assignment.global.web.BasicResponse;
import com.developer.assignment.service.member.MemberSearchService;
import com.developer.assignment.service.member.MemberSignUpService;
import com.developer.assignment.web.dto.member.request.MemberSearchType;
import com.developer.assignment.web.dto.member.request.SignUpRequest;
import com.developer.assignment.web.dto.member.response.MemberResponse;
import com.developer.assignment.web.dto.order.response.OrderResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemberApi {

    private final MemberSignUpService memberSignUpService;

    private final MemberSearchService memberSearchService;

    @ApiOperation(value = "회원 가입", notes = "회원을 가입합니다.", response = MemberResponse.class)
    @PostMapping("/api/v1/member")
    public BasicResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ApiResult.success(new MemberResponse(memberSignUpService.signUp(signUpRequest)));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "로그인된 회원을 탈퇴합니다.")
    @PostMapping("/api/v1/member/signout")
    public BasicResponse signOut(
            @ApiIgnore @Session SessionMember sessionMember
    ) {
        memberSignUpService.signOut(sessionMember);
        return ApiResult.success();
    }

    @ApiOperation(value = "단일 회원 상세 정보 조회", notes = "단일 회원의 상세정보를 조회합니다.")
    @GetMapping("/api/v1/member/{id}")
    public BasicResponse findMemberById(
            @PathVariable(value = "id") Long id
    ) {
        return ApiResult.success(new MemberResponse(memberSearchService.findMemberById(id)));
    }

    @ApiOperation(value = "단일 회원의 주문 목록 조회", notes = "단일 회원의 주문 목록을 조회합니다.")
    @GetMapping("/api/v1/member/{id}/orders")
    public BasicResponse findOrdersByMember(
            @ApiParam(name = "id", value = "회원 고유 번호", required = true) @PathVariable(value = "id") Long id
    ) {
        return ApiResult.success(memberSearchService.findOrdersByMember(id).stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList()));
    }

    @ApiPageable
    @ApiOperation(value = "여러 회원 목록 조회", notes = "여러 회원 목록 조회합니다. \n 페이지네이션을 제공, 이름과 이메일에 대한 키워드 검색 그리고 검색 결과에 해당 회원의 마지막 주문을 포함하여 조회됩니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchType", value = "검색 타입", dataType = "string", paramType = "query", dataTypeClass = MemberSearchType.class),
            @ApiImplicitParam(name = "keyword", value = "검색 단어", dataType = "string", paramType = "query"),
    })
    @GetMapping("/api/v1/members")
    public BasicResponse getMembers(MemberSearchType memberSearchType, String keyword, @PageableDefault Pageable pageable) {
        return ApiResult.success(memberSearchService.pagingFindMembers(memberSearchType, keyword, pageable));
    }

}
