package com.ievlev.faceit.service;

import com.ievlev.faceit.exceptions.UserIdNotFoundException;
import com.ievlev.faceit.model.User;
import com.ievlev.faceit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLoadUserByUsername_WhenUserDoesNotExist_ShouldThrowUsernameNotFoundException() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }

    @Test
    public void testGetByUsername_WhenUserExists_ShouldReturnUser() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        User result = userService.getByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testGetByUsername_WhenUserDoesNotExist_ShouldThrowUsernameNotFoundException() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.getByUsername(username));
    }

    @Test
    public void testGetUserById_WhenUserExists_ShouldReturnUser() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    public void testGetUserById_WhenUserDoesNotExist_ShouldThrowUserIdNotFoundException() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserIdNotFoundException.class, () -> userService.getUserById(userId));
    }
}
