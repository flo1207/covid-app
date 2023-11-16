package org.polytech.covid.auth;

import org.polytech.covid.User.service.customUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class securityConfig {

    private final customUserDetailsService customUserDetailsService;
    private final String jwtSecret;

    @Autowired
    public securityConfig( customUserDetailsService customUserDetailsService, @Value("${jwt.secret}") String jwtSecret) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtSecret = jwtSecret;
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_SUPER > ROLE_ADMIN\n" +
                "ROLE_ADMIN > ROLE_MDC\n" +
                "ROLE_MDC > ROLE_GUEST");
        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(new jwtAuthenticationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests((authz) ->
                authz.antMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/private/**").hasAuthority("ROLE_SUPER")
                        .antMatchers(HttpMethod.POST, "/api/private/**").hasAuthority("ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/private/**").hasAuthority("ROLE_MDC"))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//On rend les session stateless
        .and() 
        .userDetailsService(customUserDetailsService) // Utilisation de votre CustomUserDetailsService
        .csrf().disable();
        return http.build();
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}