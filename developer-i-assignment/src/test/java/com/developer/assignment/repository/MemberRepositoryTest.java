package com.developer.assignment.repository;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.MemberTestBuilder;
import com.developer.assignment.domain.member.model.Email;
import com.developer.assignment.global.config.MasterDataSourceConfig;
import com.developer.assignment.repository.master.MemberMasterRepository;
import com.developer.assignment.test.config.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(MasterDataSourceConfig.class)
public class MemberRepositoryTest extends RepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberMasterRepository memberMasterRepository;

    @BeforeEach
    public void tearDown() {
        memberMasterRepository.deleteAll();
    }

    @DisplayName("회원 테이블 : 단일 저장 및 조회")
    @Test
    public void memberMaster_singleSave() {
        // given
        Email email = Email.of( "single-save-1@single.save.test.com" );
        Member member = MemberTestBuilder.generate( email );

        // when
        testEntityManager.persist(member);

        // then
        assertTrue(memberMasterRepository.findById(member.getId()).isPresent());
    }

    @DisplayName("회원 테이블 : N 건 저장 및 목록 조회")
    @Test
    public void memberMaster_listSave() {
        // given
        for ( int i = 0; i < 10; i++ ) {
            Email email = Email.of( "multi-save-" +i+ "@multi.save.test.com" );
            Member member = MemberTestBuilder.generate(email);

            // when
            testEntityManager.persist(member);
        }

        // then
        assertEquals( 10, memberMasterRepository.findAll().size());
    }

    @DisplayName("회원 테이블 : 단일 저장 및 삭제")
    @Test
    public void memberMaster_saveDelete() {
        // given
        Email email = Email.of( "single-delete@single.delete.test.com" );
        Member member = MemberTestBuilder.generate( email );

        // when
        testEntityManager.persist(member);
        assertTrue(memberMasterRepository.findById(member.getId()).isPresent());

        // then
        memberMasterRepository.delete(member);
        assertThat(memberMasterRepository.findById(member.getId())).isEmpty();
    }

}
