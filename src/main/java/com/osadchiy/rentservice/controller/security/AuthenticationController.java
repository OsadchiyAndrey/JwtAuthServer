package com.osadchiy.rentservice.controller.security;

import com.osadchiy.rentservice.domain.DataResponseEntity;
import com.osadchiy.rentservice.domain.authorization.AuthorizationRequest;
import com.osadchiy.rentservice.domain.authorization.AuthorizationResponse;
import com.osadchiy.rentservice.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/authenticate")
    @Operation(summary = "Authentication request", description = "Request for authorization and getting jwt token")
    public DataResponseEntity<AuthorizationResponse> createAuthenticationToken(@RequestBody AuthorizationRequest request) {
        String email = request.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
        return new DataResponseEntity<>(new AuthorizationResponse(email, generateToken(request)));
    }

    private String generateToken(AuthorizationRequest request) {
        return  jwtTokenProvider.generateToken(request.getEmail());
    }



}
