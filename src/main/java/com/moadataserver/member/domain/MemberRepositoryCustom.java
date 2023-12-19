package com.moadataserver.member.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface MemberRepositoryCustom {
    public Optional<Member> findMemberByEmail(String email);

    public Optional<Member> findMemberById(Long id);
    public Page<Member> searchMember(Long id, String name, String email, Integer type, PageRequest pageRequest);

    public Page<Member> findAllMembers(PageRequest pageRequest);

}
