package org.polytech.covid.User.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.polytech.covid.User.files.user;
import org.polytech.covid.User.files.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class userService{

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * On enregistre un super utilisateur au demarrage s'il n'est pas déjà enregistré
     */
    @PostConstruct
    public void createUserDefault() {
        user sup = userRepository.findByMail("super_admin@gmail.com");
        if(sup == null){
            System.out.println("Creation du super user");
            user user = new user();
            user.setNom("user");
            SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_SUPER");
            String encodedPassword = passwordEncoder.encode("super");
            user.setMail("super_admin@gmail.com");
            user.setNom("super");
            user.setPrenom("super_admin");
            user.setPassword(encodedPassword);
            user.setRole(role_user);
            this.userRepository.save(user);
        }
    }
    

    public user saveAll(user user){
        return userRepository.save(user);
    }

    public List<user> getByIdCenter(Long convert_id) {
        return userRepository.getByCenter(convert_id);
    }

    public user findAllByIdUser(Long convert_id) {
        return userRepository.findAllByIdUser(convert_id);
    }

    public List<user> findAll() {
        return userRepository.findAll();
    }

    public user[] findAllByRole(SimpleGrantedAuthority role_user) {
        return userRepository.findAllByRole(role_user);
    }

    public user findAllByMailAndPassword(String username, String password) {
        return userRepository.findByMailAndPassword(username, password);
    }

    public List<user> findByCenterIdCentre(Long convert_id) {
        return userRepository.findByCenterIdCentre(convert_id);
    }

    public user findByMail(String username) {
        return userRepository.findByMail(username);
    }

    public void delete(user user) {
        userRepository.delete(user);
    }

}
