package com.developer.assignment.repository.second;

import com.developer.assignment.domain.member.QMember;
import com.developer.assignment.domain.order.QOrder;
import com.developer.assignment.web.dto.member.request.MemberSearchType;
import com.developer.assignment.web.dto.member.response.MembersAndLastOrderResponse;
import com.developer.assignment.web.dto.member.response.QMembersAndLastOrderResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MembersAndLastOrderResponse> pagingFindMembers(MemberSearchType type, String keyword, Pageable pageable) {
        // dsl 엔티티 초기화 ("별칭")
        QMember member = new QMember("m");
        QOrder order = new QOrder("q");

        // where 조건 생성
        BooleanBuilder whereBuilder = whereCondition(member, type, keyword);

        QueryResults<MembersAndLastOrderResponse> results = queryFactory
                .select(new QMembersAndLastOrderResponse(
                        member.name,
                        member.nickname,
                        member.phoneNumber,
                        member.email,
                        member.gender,
                        order.id,
                        order.productName,
                        order.paymentDate
                ))
                // from join
                .from(member)
                .leftJoin(order).on(member.id.eq(order.member.id))
                // where
                .where( whereBuilder )
                // group by
                .groupBy( member.id )
                // paging
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanBuilder whereCondition(QMember member, MemberSearchType type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        if ( keyword == null ) {
            return builder;
        }

        switch (type) {
            case NAME: {
                builder.and( member.name.value.contains(keyword) );
                break;
            }
            case EMAIL: {
                builder.and( member.email.value.contains(keyword) );
                break;
            }
            default: {
                // nothing
            }
        }
        return builder;
    }

}
