package com.example.cloudfilestorage.config;
import com.example.cloudfilestorage.model.User; import com.example.cloudfilestorage.repository.UserRepository; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner admin(UserRepository r,PasswordEncoder p){return a->{if(!r.existsByUsername("admin"))r.save(new User("admin",p.encode("admin123"),"ADMIN"));};}
}