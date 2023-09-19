package ru.netology.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)  // prePostEnabled = true, - не указываю, потому что дефолтная
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/", "/all-cities").permitAll()
                        //.requestMatchers("/persons/").hasRole("WRITE")
                        .anyRequest().authenticated())
                .formLogin(
                        (form) -> form.permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails userR = User.withDefaultPasswordEncoder()
                .username("Kim")
                .password("111")
                .roles("READ")
                .build();
        UserDetails userW = User.withDefaultPasswordEncoder()
                .username("Pak")
                .password("222")
                .roles("WRITE")
                .build();
        UserDetails userD = User.withDefaultPasswordEncoder()
                .username("Ten")
                .password("333")
                .roles("DELETE")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Li")
                .password("444")
                .roles("READ", "WRITE")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("adm")
                .password("123")
                .roles("READ", "WRITE", "DELETE")
                .build();
        return new InMemoryUserDetailsManager(userR, userW, userD, user, admin);
    }

}
