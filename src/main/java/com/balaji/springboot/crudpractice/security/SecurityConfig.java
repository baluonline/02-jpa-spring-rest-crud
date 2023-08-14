package com.balaji.springboot.crudpractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer.
                        requestMatchers(HttpMethod.GET,"/api/v1/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/v1/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/v1/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/employee/**").hasRole("ADMIN")
        );
//        use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request forgery
        // in general, not required for stateless REST apis that use POST, PUT, DELETE and/or FETCH
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();

    }
}
