package org.polytech.covid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.polytech.covid.User.service.userService;
import org.assertj.core.api.Assertions;

@SpringBootTest
public class authServiceTest {
    @Autowired
    userService userService;

    // @Test
    // public void testSuper() {
    //     Assertions.assertThat(this.userService.findByMail("super_admin@gmail.com").getMail()).isEqualTo("super_admin@gmail.com");
    // }
  
}
