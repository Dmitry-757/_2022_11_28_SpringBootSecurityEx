package org.dng.springbootsecurityex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();

        User.builder()
                .username("user")
                .password(bCryptPasswordEncoder.encode("userPass"))
                .roles("USER")
                .username("Admin")
                .password(bCryptPasswordEncoder.encode("adminPass"))
                .roles("ADMIN")
                .build();
        List<UserDetails> ul = List.of(
                User.builder()
                        .username("user")
                        .password(bCryptPasswordEncoder.encode("userPass"))
                        .roles("USER")
                        .build(),
                User.builder()
                        .username("Admin")
                        .password(bCryptPasswordEncoder.encode("adminPass"))
                        .roles("ADMIN")
                        .build()
        );
        return new InMemoryUserDetailsManager(ul);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/", "/mylogin", "/resources/**").permitAll() //сюда можно всем
//                .anyRequest().authenticated()//а в остальные запросы - только авторизованные
                .and()
//                .formLogin((form) -> form
//                        .loginProcessingUrl("/myLoginProcess")
//                        .loginPage("/mylogin")
//                        .defaultSuccessUrl("/hello", true)
//                        .permitAll()
//                )
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/hello")
////                .failureUrl("/login?error=true")
//                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable()
                ;
//                .hasRole("USER")
//                .and()
////                .formLogin()
//                .formLogin((form) -> form
//                        .loginPage("/login.html")
//                        .defaultSuccessUrl("/", true)
//                        .permitAll()
//                )
//                .logout()
//                .logoutSuccessUrl("/login.html")
//                .and()
//                .csrf()
//                .disable();
//        ;



        return http.build();
    }

}
