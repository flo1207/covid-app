package org.polytech.covid.User.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    public List<User> findAll();
    public List<User> getByCenter(Long convert_id);
    public User getById(Long convert_id);
    public User findAllByRole(Integer convert_id);
}
