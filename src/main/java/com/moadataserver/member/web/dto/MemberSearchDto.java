package com.moadataserver.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MemberSearchDto(@JsonProperty(value = "id") Long id, @JsonProperty(value = "name") String name,
                              @JsonProperty(value = "email") String email, @JsonProperty(value = "type") Integer type) {
}
