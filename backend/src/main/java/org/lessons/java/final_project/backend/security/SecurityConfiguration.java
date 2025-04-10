package org.lessons.java.final_project.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/videogiochi/create", "/videogiochi/update/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/videogiochi/**").hasAuthority("ADMIN")
                .requestMatchers("/generi/create", "/generi/update/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/generi/**").hasAuthority("ADMIN")
                .requestMatchers("/console/create", "/console/update/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/console/**").hasAuthority("ADMIN")
                .requestMatchers("/videogiochi", "/videogiochi/**", "/generi",  "/generi/**", "/console", "/console/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                    .defaultSuccessUrl("/videogiochi", true)
                .and().logout()
                .and().exceptionHandling();

        return http.build();
    }

    @Bean
    DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
