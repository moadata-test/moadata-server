package com.moadataserver.member.Service;

import com.moadataserver.member.domain.Member;
import com.moadataserver.member.domain.MemberRepository;
import com.moadataserver.member.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.moadataserver.common.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 로직
     *
     * @param dto
     * @return
     */
    public Long joinMember(MemberJoinDto dto) {
        Member joinMember = Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .type(1)
                .phoneNumber(dto.phoneNumber())
                .build();
        return memberRepository.save(joinMember).getId();
    }

    /**
     * 메일 수정 로직
     * @param dto
     */
    public void updateMemberEmail(MemberEmailUpdateDto dto) {
        // 메일 중복 검증
        if (memberRepository.findMemberByEmail(dto.email()).isEmpty()) {
            Member findMember = memberRepository.findMemberById(dto.id()).get();
            findMember.updateEmail(dto.email());
        } else {
            throw new IllegalArgumentException(DUPLICATED_MAIL_EXISTS);
        }

    }

    /**
     * 비밀번호 수정 로직
     * @param dto
     */
    public void updateMemberPassword(MemberPasswordUpdateDto dto) {
        Member findMember = memberRepository.findMemberById(dto.id()).get();
        findMember.updatePassword(dto.password());
    }

    /**
     * 회원 정보 수정 로직
     * @param dto
     */
    public void updateMember(MemberUpdateDto dto) {
        Member findMember = memberRepository.findMemberById(dto.id()).get();
        findMember.updateMember(dto.name(), dto.type(), dto.phoneNumber());
    }

    /**
     * 회원 삭제 로직
     * @param id
     */
    public void deleteMember(Long id) {
        Member findMember = memberRepository.findMemberById(id).get();
        findMember.deleteSoftly(LocalDateTime.now());
    }

    /**
     * 회원 삭제 취소 로직
     * @param id
     */
    public void unDoDeleteMember(Long id) {
        Member findMember = memberRepository.findById(id).get();
        findMember.undoDeletion();
    }

    /**
     * 전체 회원 조회 로직
     * @return
     */
    @Transactional(readOnly = true)
    public Page<MemberDto> findAllMembers(PageRequest pageRequest) {
        Page<Member> page = memberRepository.findAllMembers(pageRequest);
        List<MemberDto> memberDtos = page.getContent().stream().map(member ->
                        new MemberDto(member.getId(), member.getName(),
                                member.getEmail(), member.getPassword(),
                                member.getType(), member.getPhoneNumber())).
                collect(Collectors.toList());

        return new PageImpl<>(memberDtos, pageRequest, page.getSize());
    }

    /**
     * 회원 검색 로직
     * @param dto
     * @return
     */
    @Transactional(readOnly = true)
    public Page<MemberDto> searchMembers(MemberSearchDto dto, PageRequest pageRequest) {
        log.info("dto = {}", dto);
        Page<Member> searchMembers = memberRepository.searchMember(dto.id(), dto.name(), dto.email(), dto.type(), pageRequest);
        List<MemberDto> result = searchMembers.getContent().stream().map(member ->
                        new MemberDto(member.getId(), member.getName(),
                                member.getEmail(), member.getPassword(),
                                member.getType(), member.getPhoneNumber())).
                collect(Collectors.toList());
        return new PageImpl<>(result, searchMembers.getPageable(), searchMembers.getSize());
    }
}
