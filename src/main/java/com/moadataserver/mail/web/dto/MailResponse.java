package com.moadataserver.mail.web.dto;

public record MailResponse<T>(Integer status, String message, T data) {
}
