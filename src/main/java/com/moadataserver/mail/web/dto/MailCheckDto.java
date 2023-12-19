package com.moadataserver.mail.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MailCheckDto(@JsonProperty(value = "id") Long id,
                           @JsonProperty(value = "email")
                           @Email
                           @NotBlank
                           String email,
                           @JsonProperty(value = "authCode") String authCode) {
}
