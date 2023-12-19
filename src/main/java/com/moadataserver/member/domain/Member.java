package com.moadataserver.member.domain;

import com.moadataserver.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME", nullable = false)
    private String name;

    @Column(name = "MEMBER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "MEMBER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "MEMBER_TYPE", nullable = false)
    private Integer type;

    @Column(name = "MEMBER_MOBILE_NO", nullable = false)
    private String phoneNumber;
}
