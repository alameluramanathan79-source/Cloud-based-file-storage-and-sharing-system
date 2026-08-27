package com.example.cloudfilestorage.config;
import com.example.cloudfilestorage.model.User; 
import com.example.cloudfilestorage.repository.UserRepository;
import java.util.List;
import org.springframework.context.annotation.*; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.*;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService users(UserRepository repo){return u->{User x=repo.findByUsername(u).orElseThrow(()->new UsernameNotFoundException("User not found"));return new org.springframework.security.core.userdetails.User(x.getUsername(),x.getPassword(),List.of(new SimpleGrantedAuthority("ROLE_"+x.getRole())));};}
 @Bean SecurityFilterChain filter(HttpSecurity h)throws Exception{return h.authorizeHttpRequests(a->a.requestMatchers("/login","/register","/css/**").permitAll().anyRequest().authenticated()).formLogin(f->f.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll()).logout(l->l.logoutSuccessUrl("/login?logout")).build();}
}