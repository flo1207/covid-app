package org.polytech.covid.auth;

import javax.servlet.http.HttpServletResponse;

import org.polytech.covid.User.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @EnableWebSecurity
    @Configuration(proxyBeanMethods = false)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        
        @Value("${jwt.secret}")
        private String jwtSecret;

        private final CustomUserDetailsService customUserDetailsService;

        public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
            this.customUserDetailsService = customUserDetailsService;
        }


        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE + 1)
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {      
            http
                .addFilterBefore(new JwtAuthenticationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .mvcMatchers("/api/public/**").permitAll()
                    .mvcMatchers("/api/private/**").hasAnyRole("ADMIN", "SUPER", "MDC")
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) ->{
                    System.out.println(authException);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erreur d'authentification");})
                .accessDeniedHandler((request, response, accessDeniedException) ->{
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");});
    }
        
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.inMemoryAuthentication()
        //     .withUser("jean.lais@gmail.com")
        //     .password(encoder().encode("admin"))
        //     .roles("ADMIN")
        //     .and()
        //     .withUser("flo1207@live.fr")
        //     .password(encoder().encode("super"))
        //     .roles("SUPER")
        //     .and()
        //     .withUser("eric.p@gmail.com")
        //     .password(encoder().encode("mdc"))
        //     .roles("MDC");

        auth.userDetailsService(customUserDetailsService)
            .passwordEncoder(encoder());

    }

    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    
}