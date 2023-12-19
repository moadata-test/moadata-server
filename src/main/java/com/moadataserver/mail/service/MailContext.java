package com.moadataserver.mail.service;

public interface MailContext {

    String AUTH_MAIL_SUBJECT = "모아데이타 회원가입 인증번호";
    String AUTH_MAIL_FORMAT = """
            안녕하세요, 모아데이타 회원 인증 메일입니다.
            회원 가입 창 메일인증란에 %s를 입력해주세요.
            """;
}
