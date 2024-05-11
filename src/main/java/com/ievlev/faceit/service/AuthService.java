package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.AuthRequest;
import com.ievlev.faceit.dto.CustomUserDetailsDto;
import com.ievlev.faceit.dto.JwtResponse;
import com.ievlev.faceit.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(AuthRequest authRequest) {
        if (authRequest == null) {
            throw new IllegalArgumentException("auth request can't be null");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); //check if login and password is correct, if not that throw BadCredentialsException
        CustomUserDetailsDto userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
