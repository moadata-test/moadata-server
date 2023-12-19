package com.moadataserver.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member ,Long>, MemberRepositoryCustom {
}
