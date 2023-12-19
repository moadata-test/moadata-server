package com.moadataserver.common.message;

public interface ResponseMessage {
    // member
    String MEMBER_JOIN_SUCCESS = "회원 가입 성공";
    String MEMBER_UPDATE_PASSWORD_SUCCESS = "회원 비밀번호 수정 성공";
    String MEMBER_UPDATE_EMAIL_SUCCESS = "회원 이메일 수정 성공";
    String MEMBER_UPDATE_SUCCESS = "회원 수정 성공";
    String MEMBER_DELETE_SUCCESS = "회원 삭제 성공";
    String MEMBER_UNDO_DELETE_SUCCESS = "회원 복구 성공";
    String MEMBER_FIND_ALL_SUCCESS= "전체 회원 조회 성공";
    String MEMBER_SEARCH_SUCCESS= "회원 검색 성공";

    // 메일
    String MAIL_CREATE_SUCCESS= "인증 메일 생성 및 발송 성공";
    String MAIL_CHECK_SUCCESS= "메일 인증 성공";
}
