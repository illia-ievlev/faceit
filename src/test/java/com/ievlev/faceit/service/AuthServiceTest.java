package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.AuthRequest;
import com.ievlev.faceit.dto.CustomUserDetailsDto;
import com.ievlev.faceit.dto.JwtResponse;
import com.ievlev.faceit.util.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {
    @Test
    public void testCreateAuthToken() {
        AuthRequest authRequest = new AuthRequest("username", "password");
        CustomUserDetailsDto userDetails = new CustomUserDetailsDto("username", "password", Collections.emptyList(), "12");
        UserService userService = mock(UserService.class);
        when(userService.loadUserByUsername("username")).thenReturn(userDetails);
        JwtTokenUtils jwtTokenUtils = mock(JwtTokenUtils.class);
        when(jwtTokenUtils.generateToken(userDetails)).thenReturn("token");
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        AuthService authService = new AuthService(userService, jwtTokenUtils, authenticationManager);
        JwtResponse jwtResponse = authService.createAuthToken(authRequest);
        assertEquals("token", jwtResponse.getToken());
    }

    @Test
    public void testCreateAuthTokenWithInvalidAuthRequest() {
        AuthService authService = new AuthService(null, null, null);
        assertThrows(IllegalArgumentException.class, () -> authService.createAuthToken(null));
    }
}
