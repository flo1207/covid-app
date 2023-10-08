package org.polytech.covid.User.service;

import java.util.Collections;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMail(username);

        if (user == null) {
            System.out.println("aucun");
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }else{
            System.out.println(user.getMail());
        }
        // Créez un objet UserDetails à partir des informations de l'utilisateur
        return new org.springframework.security.core.userdetails.User(
            user.getMail(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(   "ADMIN")) // Ajoutez les rôles ou les autorisations appropriés ici
        );
    }
}