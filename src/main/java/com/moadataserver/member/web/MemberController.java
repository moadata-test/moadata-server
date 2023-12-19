package com.moadataserver.member.web;

import com.moadataserver.member.Service.MemberService;
import com.moadataserver.member.web.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moadataserver.common.message.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse<Long>> joinMember(@Valid @RequestBody MemberJoinDto dto) {
        Long id = memberService.joinMember(dto);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_JOIN_SUCCESS, id));
    }

    @PutMapping("/password")
    public ResponseEntity<MemberResponse<Long>> updateMemberPassword(@Valid @RequestBody MemberPasswordUpdateDto dto) {
        memberService.updateMemberPassword(dto);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_UPDATE_PASSWORD_SUCCESS, null));
    }

    @PutMapping("/email")
    public ResponseEntity<MemberResponse<Long>> updateMemberEmail(@Valid @RequestBody MemberEmailUpdateDto dto) {
        memberService.updateMemberEmail(dto);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_UPDATE_EMAIL_SUCCESS, null));
    }
    @PutMapping()
    public ResponseEntity<MemberResponse<Long>> updateMember(@Valid @RequestBody MemberUpdateDto dto) {
        memberService.updateMember(dto);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_UPDATE_SUCCESS, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponse<Long>> deleteMember(@PathVariable(value = "id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_DELETE_SUCCESS, null));
    }
    @DeleteMapping("/undo/{id}")
    public ResponseEntity<MemberResponse<Long>> undoDeleteMember(@PathVariable(value = "id") Long id) {
        memberService.unDoDeleteMember(id);
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_UNDO_DELETE_SUCCESS, null));
    }

    @GetMapping
    public ResponseEntity<MemberResponse<Page<MemberDto>>> findAllMembers(@RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "9999") Integer size) {
        Page<MemberDto> findMembers = memberService.findAllMembers(PageRequest.of(page, size));
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_FIND_ALL_SUCCESS, findMembers));
    }

    @GetMapping("/search")
    public ResponseEntity<MemberResponse<Page<MemberDto>>> serachMembers(@RequestParam(defaultValue = "0") Integer page,
                                                                         @RequestParam(defaultValue = "9999") Integer size,
                                                                         @RequestParam(value = "id", required = false) Long id,
                                                                         @RequestParam(value = "name", required = false) String name,
                                                                         @RequestParam(value = "email", required = false) String email,
                                                                         @RequestParam(value = "type", required = false) Integer type) {
        Page<MemberDto> findMembers = memberService.searchMembers(new MemberSearchDto(id, name, email, type), PageRequest.of(page, size));
        return ResponseEntity.ok(new MemberResponse<>(OK.value(), MEMBER_SEARCH_SUCCESS, findMembers));
    }
}
