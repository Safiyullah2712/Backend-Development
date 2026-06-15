package com.student.management.security;

import com.student.management.model.User;
import com.student.management.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(
            UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        User user =
                repository.findByUsername(
                        username);

        if (user == null) {

            throw new UsernameNotFoundException(
                    "User Not Found");
        }

        return new CustomUserDetails(
                user);
    }
}