package com.developer.assignment.domain.member;

import com.developer.assignment.domain.member.model.*;
import com.developer.assignment.domain.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@ToString
@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false, length = 20))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "nickname", nullable = false, length = 30))
    private NickName nickname;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false, length = 200))
    private Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone_number", nullable = false, length = 40))
    private PhoneNumber phoneNumber;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, length = 200, unique = true))
    private Email email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Name name, NickName nickname, Password password, PhoneNumber phoneNumber, Email email, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

}
