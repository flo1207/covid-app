package org.polytech.covid.User.service;

import org.polytech.covid.User.files.customUserDetails;
import org.polytech.covid.User.files.user;
import org.polytech.covid.User.files.userRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailsService implements UserDetailsService {
    private final userRepository userRepository;

    public customUserDetailsService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = userRepository.findByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        else{
            System.out.println("validation");
            return new customUserDetails(user);
        }
    }
}