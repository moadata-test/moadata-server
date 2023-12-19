package com.moadataserver.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MemberPasswordUpdateDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "password")
        @NotBlank
        @Length(min = 8)
        String password) {
}
