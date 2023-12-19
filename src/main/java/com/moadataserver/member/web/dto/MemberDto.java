package com.moadataserver.member.web.dto;

public record MemberDto(Long id,
                        String name,
                        String email,
                        String password,
                        Integer type,
                        String phoneNumber
                        ){}