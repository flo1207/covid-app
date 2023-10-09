package org.polytech.covid.auth;

import javax.servlet.http.HttpServletResponse;

import org.polytech.covid.User.JwtTokenValidator;
import org.polytech.covid.User.service.CustomUserDetailsService;
import org.polytech.covid.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.DispatcherType;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @EnableWebSecurity
    @Configuration(proxyBeanMethods = false)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        
        @Value("${jwt.secret}")
        private String jwtSecret;

        private CustomUserDetailsService userDetailsService;


        @Autowired
        public SecurityConfig(CustomUserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE + 1)
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

    
        @Override
        protected void configure(HttpSecurity http) throws Exception {      
            http
                .authorizeRequests()
                    .mvcMatchers("/api/public/**").permitAll()
                    .mvcMatchers("/api/private/**").hasAnyRole("ADMIN", "SUPER", "MDC")
                    .and()
                .httpBasic()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) ->{
                    System.out.println("1");
                    System.out.println(authException);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "1Erreur d'authentification");})
                .accessDeniedHandler((request, response, accessDeniedException) ->{
                    System.out.println("1");
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "2Accès refusé");});
    }
        
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("jean.lais@gmail.com")
        .password(encoder().encode("admin"))
        .roles("ADMIN")
        .and()
        .withUser("flo1207@live.fr")
        .password(encoder().encode("super"))
        .roles("SUPER");

    }

    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService)
    //         .passwordEncoder(encoder());
    // }  

       

    
    }