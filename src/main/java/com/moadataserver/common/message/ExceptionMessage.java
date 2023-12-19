package com.moadataserver.common.message;

public interface ExceptionMessage {
    // member
    String DUPLICATED_MAIL_EXISTS = "해당 메일로 가입된 계정이 존재합니다.";
    String WRONG_AUTH_CODE = "잘못된 인증 코드입니다.";
}
