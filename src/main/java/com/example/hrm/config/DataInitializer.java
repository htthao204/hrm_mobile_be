package com.example.hrm.config;

import com.example.hrm.entity.Account;
import com.example.hrm.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (accountRepository.count() == 0) {

            Account admin = new Account();
            admin.setUsername("admin");

            admin.setPassword(
                    passwordEncoder.encode("123456")
            );

            accountRepository.save(admin);
        }
    }
}