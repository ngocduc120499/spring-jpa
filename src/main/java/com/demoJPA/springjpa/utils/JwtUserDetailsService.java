package com.demoJPA.springjpa.utils;

import java.util.ArrayList;
import java.util.Optional;

import com.demoJPA.springjpa.entity.User;
import com.demoJPA.springjpa.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(s);
        User object = user.orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return object;
    }
}