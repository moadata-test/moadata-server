package com.moadataserver.mail.service;

import com.moadataserver.config.MailConfig;
import com.moadataserver.mail.domain.Mail;
import com.moadataserver.mail.domain.MailRepository;
import com.moadataserver.mail.web.dto.MailCheckDto;
import com.moadataserver.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Random;

import static com.moadataserver.common.message.ExceptionMessage.DUPLICATED_MAIL_EXISTS;
import static com.moadataserver.common.message.ExceptionMessage.WRONG_AUTH_CODE;
import static com.moadataserver.mail.service.MailContext.AUTH_MAIL_FORMAT;
import static com.moadataserver.mail.service.MailContext.AUTH_MAIL_SUBJECT;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;
    private final MemberRepository memberRepository;
    private final MailRepository mailRepository;

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(mailConfig.getUsername());
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Transactional()
    public Long createAuthMail(MailCheckDto dto) {
        findDuplicatedMail(dto.email());

        String randomNumber = createRandomNumber();

        Mail mail = Mail.builder()
                .authCode(randomNumber)
                .authMail(dto.email())
                .build();

        Mail savedMail = mailRepository.save(mail);

        sendMail(dto.email(), AUTH_MAIL_SUBJECT, AUTH_MAIL_FORMAT.formatted(randomNumber));

        return savedMail.getId();
    }

    @Transactional
    public void checkAuthMail(MailCheckDto dto) {
        Mail mail = mailRepository.findById(dto.id()).get();
        if (!Objects.equals(dto.authCode(), mail.getAuthCode())) {
            throw new IllegalArgumentException(WRONG_AUTH_CODE);
        }
        mailRepository.delete(mail);
    }

    private void findDuplicatedMail(String email) {
        if (memberRepository.findMemberByEmail(email).isPresent())
            throw new IllegalArgumentException(DUPLICATED_MAIL_EXISTS);
    }

    private String createRandomNumber() {
        String characters = "0123456789";

        // 랜덤 객체 생성
        Random random = new Random();

        // StringBuilder를 사용하여 문자열 빌드
        StringBuilder verificationCodeBuilder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            // 문자열에서 랜덤하게 하나의 문자 선택하여 추가
            int randomIndex = random.nextInt(characters.length());
            verificationCodeBuilder.append(characters.charAt(randomIndex));
        }

        return verificationCodeBuilder.toString();
    }
}
