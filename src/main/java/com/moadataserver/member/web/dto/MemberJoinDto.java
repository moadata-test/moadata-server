package com.moadataserver.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record MemberJoinDto(
        @JsonProperty(value = "name")
        @NotBlank
        String name,
        @JsonProperty(value = "email")
        @Email
        @NotBlank
        String email,
        @JsonProperty(value = "password")
        @NotBlank
        @Length(min = 8)
        String password,
        @JsonProperty(value = "type")
        @Min(value = 0)
        @Max(value = 1)
        String type,
        @JsonProperty(value = "phoneNumber")
        @NotBlank
        String phoneNumber) {
}
