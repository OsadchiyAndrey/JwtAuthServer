package com.osadchiy.rentservice.domain.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
