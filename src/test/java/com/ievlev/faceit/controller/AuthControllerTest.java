package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.AuthRequest;
import com.ievlev.faceit.dto.JwtResponse;
import com.ievlev.faceit.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    public void testCreateAuthToken() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testUsername");
        authRequest.setPassword("testPassword");
        JwtResponse jwtResponse = new JwtResponse("testToken");
        when(authService.createAuthToken(authRequest)).thenReturn(jwtResponse);
        JwtResponse result = authController.createAuthToken(authRequest);
        assertEquals(jwtResponse, result);
        verify(authService, times(1)).createAuthToken(authRequest);
    }
}
