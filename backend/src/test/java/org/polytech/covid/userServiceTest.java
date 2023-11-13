package org.polytech.covid;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.polytech.covid.User.files.user;
import org.polytech.covid.User.files.userRepository;
import org.polytech.covid.User.service.userService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.assertj.core.api.Assertions;

@SpringBootTest
public class userServiceTest {
    
    @Autowired
    userService userService;

    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // @Test
    // public void testAjoutSupprUser() {
        
    //     user user = new user();
        
    //     user.setPrenom("jean");
    //     user.setNom("Dupont");
    //     user.setMail("jean.dupont@gmail.com");
    //     SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_SUPER");
    //     String encodedPassword = passwordEncoder.encode("mdc");
    //     user.setRole(role_user);
    //     user.setPassword(encodedPassword);

    //     this.userRepository.save(user);

    //     // Assertions.assertThat(result).isTrue();
    //     // Assertions.assertThat(user.get()).isEqualTo(9);
    //     Assertions.assertThat(this.userRepository.findByMail("jean.dupont@gmail.com").getMail()).isEqualTo("jean.dupont@gmail.com");
    //     this.userRepository.delete(user);
    //     Assertions.assertThat(this.userRepository.findByMail("jean.dupont@gmail.com")).isNull();
    // }
    
}
