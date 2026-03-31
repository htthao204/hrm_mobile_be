package com.example.hrm.service.impl;

import com.example.hrm.dto.response.LoginResponse;
import com.example.hrm.entity.Account;
import com.example.hrm.entity.Role;
import com.example.hrm.repository.AccountRepository;
import com.example.hrm.repository.RoleRepository;
import com.example.hrm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));
    }

    public Account create(Account account) {

        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }

        // ✅ MÃ HÓA PASSWORD
        account.setPassword(
                passwordEncoder.encode(account.getPassword())
        );

        if (account.getRole() != null && account.getRole().getId() != null) {

            Role role = roleRepository.findById(account.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role không tồn tại!"));

            account.setRole(role);
        }

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy tài khoản với ID: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Override
    public LoginResponse login(String username, String password) {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return LoginResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .role(account.getRole().getName())
                .build();
    }
}