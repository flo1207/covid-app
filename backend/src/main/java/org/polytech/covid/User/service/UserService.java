package org.polytech.covid.User.service;

import java.util.List;

import org.polytech.covid.User.files.User;
import org.polytech.covid.User.files.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    public User saveAll(User user){
        return userRepository.save(user);
    }

    public List<User> getByIdCenter(Long convert_id) {
        return userRepository.getByCenter(convert_id);
    }

    public User getById(Long convert_id) {
        return userRepository.getById(convert_id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findAllByRole(Integer convert_id) {
        return userRepository.findAllByRole(convert_id);
    }
    
}
