package com.example.hrm.config;

import com.example.hrm.entity.Account;
import com.example.hrm.entity.Role;
import com.example.hrm.repository.AccountRepository;
import com.example.hrm.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        System.out.println("INIT DATABASE...");

        if(accountRepository.findByUsername("admin").isEmpty()){

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() ->
                            new RuntimeException("ADMIN role chưa tồn tại"));

            Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(adminRole); // ✅ QUAN TRỌNG

            accountRepository.save(admin);

            System.out.println("ADMIN CREATED");
        }
    }
}