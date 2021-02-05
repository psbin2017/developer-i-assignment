package com.developer.assignment.repository.master;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMasterRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(Email email);

}
