package com.developer.assignment.repository.second;

import com.developer.assignment.domain.member.Member;
import com.developer.assignment.domain.member.model.Email;
import com.developer.assignment.domain.member.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberSecondRepository extends JpaRepository<Member, Long> {

    Optional<Member> getMemberByEmailAndPassword(Email email, Password password);

}
