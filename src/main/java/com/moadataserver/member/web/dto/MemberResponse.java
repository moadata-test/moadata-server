package com.moadataserver.member.web.dto;

public record MemberResponse<T>(Integer status, String message, T data) {
}
