package com.moadataserver.mail.web.controller;

import com.moadataserver.mail.service.MailService;
import com.moadataserver.mail.web.dto.MailCheckDto;
import com.moadataserver.mail.web.dto.MailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.moadataserver.common.message.ResponseMessage.MAIL_CHECK_SUCCESS;
import static com.moadataserver.common.message.ResponseMessage.MAIL_CREATE_SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {
    private final MailService mailService;
    @PostMapping("/auth/create")
    public ResponseEntity<MailResponse<Long>> createAuthCode(@Valid @RequestBody MailCheckDto dto) {
        Long id = mailService.createAuthMail(dto);
        return ResponseEntity.ok(new MailResponse<>(OK.value(), MAIL_CREATE_SUCCESS, id));
    }

    @PostMapping("/auth/check")
    public ResponseEntity<MailResponse<Long>> checkAuthCode(@RequestBody MailCheckDto dto) {
        mailService.checkAuthMail(dto);
        return ResponseEntity.ok(new MailResponse<>(OK.value(), MAIL_CHECK_SUCCESS, null));
    }
}
