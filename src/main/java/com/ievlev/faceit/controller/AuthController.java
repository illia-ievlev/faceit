package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.AuthRequest;
import com.ievlev.faceit.dto.JwtResponse;
import com.ievlev.faceit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PostMapping("/api/v1/auth")
    public JwtResponse createAuthToken(@RequestBody AuthRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

}
