package com.osadchiy.rentservice.domain.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationResponse {

    private String token;
    private String email;
}
