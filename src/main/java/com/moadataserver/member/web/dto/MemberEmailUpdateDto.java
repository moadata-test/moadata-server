package com.moadataserver.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberEmailUpdateDto(@JsonProperty(value = "id") Long id,
                                   @JsonProperty(value = "email")
                                   @NotBlank
                                   @Email
                                   String email) {
}
