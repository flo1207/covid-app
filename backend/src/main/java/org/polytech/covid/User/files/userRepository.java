package org.polytech.covid.User.files;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface userRepository extends JpaRepository<user, Integer>{
    public List<user> findAll();
    public List<user> getByCenter(Long convert_id);
    public user findAllByIdUser(Long convert_id);
    public user[] findAllByRole(SimpleGrantedAuthority role);
    public user findByMailAndPassword(String username, String password);
    public List<user> findByCenterIdCentre(Long convert_id);
    public user findByMail(String username);
}
