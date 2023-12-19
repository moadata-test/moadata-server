package com.moadataserver.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MemberUpdateDto(@JsonProperty(value = "id")Long id,
                              @JsonProperty(value = "name")
                              @NotBlank
                              String name,
                              @JsonProperty(value = "type")
                              @Min(value = 0)
                              @Max(value = 1)
                              Integer type,
                              @JsonProperty(value = "phoneNumber")
                              @NotBlank
                              String phoneNumber) {
}
