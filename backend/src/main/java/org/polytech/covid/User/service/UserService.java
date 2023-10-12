package org.polytech.covid.User.service;

import java.util.Collections;
import java.util.List;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.files.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    

    public User saveAll(User user){
        return userRepository.save(user);
    }

    public List<User> getByIdCenter(Long convert_id) {
        return userRepository.getByCenter(convert_id);
    }

    public User findAllByIdUser(Long convert_id) {
        return userRepository.findAllByIdUser(convert_id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User[] findAllByRole(SimpleGrantedAuthority role_user) {
        return userRepository.findAllByRole(role_user);
    }

    public User findAllByMailAndPassword(String username, String password) {
        return userRepository.findByMailAndPassword(username, password);
    }

    public List<User> findByCenterIdCentre(Long convert_id) {
        return userRepository.findByCenterIdCentre(convert_id);
    }

    public User findByMail(String username) {
        return userRepository.findByMail(username);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }



    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     User user = userRepository.findByMail(username);

        
    //     return new org.springframework.security.core.userdetails.User(
    //                 user.getMail(),
    //                 user.getPassword(),
    //                 // Add user roles and authorities here
    //                 Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
    //             );
        
        
    // }
}
