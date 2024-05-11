package com.ievlev.faceit.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserIdFromAuthenticationUtilTest {

    @Test
    public void testGetUserIdFromAuthentication_WhenValidAuthentication_ShouldReturnUserId() {
        long expectedUserId = 12345L;
        Authentication authentication = new UsernamePasswordAuthenticationToken(expectedUserId, "password");
        long userId = UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication);
        assertEquals(expectedUserId, userId);
    }

    @Test
    public void testGetUserIdFromAuthentication_WhenNullAuthentication_ShouldThrowIllegalArgumentException() {
        Authentication authentication = null;
        assertThrows(IllegalArgumentException.class, () -> UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication));
    }

    @Test
    public void testGetUserIdFromAuthentication_WhenInvalidUserIdInAuthentication_ShouldThrowIllegalArgumentException() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("invalidUserId", "password");
        assertThrows(IllegalArgumentException.class, () -> UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication));
    }
}
