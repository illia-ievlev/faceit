package com.ievlev.faceit.service;


import com.ievlev.faceit.dto.CustomUserDetailsDto;
import com.ievlev.faceit.exceptions.UserIdNotFoundException;
import com.ievlev.faceit.model.User;
import com.ievlev.faceit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CustomUserDetailsDto loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        return new CustomUserDetailsDto(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()),
                user.getId().toString()
        );
    }

    public User getByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username can't be null");
        }
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("user '%s' not found", username)));
    }

    public User getUserById(@Min(1) long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(String.format("user with id %d not found", userId)));
    }

}
