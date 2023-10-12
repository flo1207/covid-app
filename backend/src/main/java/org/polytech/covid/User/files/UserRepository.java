package org.polytech.covid.User.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface UserRepository extends JpaRepository<User, Integer>{
    public List<User> findAll();
    public List<User> getByCenter(Long convert_id);
    public User findAllByIdUser(Long convert_id);
    public User[] findAllByRole(SimpleGrantedAuthority role);
    public User findByMailAndPassword(String username, String password);
    public List<User> findByCenterIdCentre(Long convert_id);
    public User findByMail(String username);
}
