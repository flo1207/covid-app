package org.polytech.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class CovidApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidApiApplication.class, args);
	}

	// @Bean
	// public SecurityFilterChain filterChain(HttpSecurity http)
	// 		throws Exception{
	// 			http.authorizeHttpRequests((authz) -> authz
	// 			.mvcMatchers("/api/public").permitAll()
	// 			.mvcMatchers("api/private").authenticated()
	// 	)
	// 	.sessionManagement()
	// 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	// 			return http.build();
	// }

}
