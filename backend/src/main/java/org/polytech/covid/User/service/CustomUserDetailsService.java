package org.polytech.covid.User.service;

import java.util.Collections;

import org.polytech.covid.User.files.CustomUserDetails;
import org.polytech.covid.User.files.User;
import org.polytech.covid.User.files.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        else{
            System.out.println("validation");
            return new CustomUserDetails(user);
        }
    }
}