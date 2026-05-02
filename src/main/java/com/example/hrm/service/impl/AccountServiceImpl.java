package com.example.hrm.service.impl;

import com.example.hrm.dto.response.LoginResponse;
import com.example.hrm.entity.Account;
import com.example.hrm.entity.Role;
import com.example.hrm.repository.AccountRepository;
import com.example.hrm.repository.RoleRepository;
import com.example.hrm.service.AccountService;
import com.example.hrm.service.JwtService;
import com.example.hrm.utils.AuthException;
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
    private final JwtService jwtService;

    // ================= FIND =================
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username));
    }

    // ================= CREATE =================
    @Override
    public Account create(Account account) {

        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // encode password
        account.setPassword(
                passwordEncoder.encode(account.getPassword())
        );

        // ⭐ SECURITY: không cho client tự set role
        Role role = roleRepository.findByName("EMPLOYEE")
                .orElseThrow(() ->
                        new RuntimeException("Default role EMPLOYEE not found"));

        account.setRole(role);

        account.setActive(true);
        account.setFirstLogin(true);

        return accountRepository.save(account);
    }

    // ================= GET ALL =================
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    // ================= DELETE =================
    @Override
    public void delete(Integer id) {

        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found");
        }

        accountRepository.deleteById(id);
    }
    @Override
    public void changePassword(
            String username,
            String oldPassword,
            String newPassword
    ) {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new AuthException("User not found"));

        // check account active
        if (!Boolean.TRUE.equals(account.getActive())) {
            throw new AuthException("Tài khoản đã bị vô hiệu hóa");
        }

        // check old password
        if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
            throw new AuthException("Mật khẩu cũ không đúng");
        }

        // encode new password
        account.setPassword(
                passwordEncoder.encode(newPassword)
        );

        // ⭐ FIRST LOGIN → false sau khi đổi mật khẩu
        account.setFirstLogin(false);

        accountRepository.save(account);
    }
    // ================= LOGIN =================
    @Override
    public LoginResponse login(String username, String password) {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new AuthException("Sai username hoặc mật khẩu"));

        if (!Boolean.TRUE.equals(account.getActive())) {
            throw new AuthException("Tài khoản đã bị vô hiệu hóa");
        }

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new AuthException("Sai username hoặc mật khẩu");
        }

        String accessToken =
                jwtService.generateToken(
                        account.getUsername(),
                        account.getRole().getName()
                );

        String refreshToken =
                jwtService.generateRefreshToken(
                        account.getUsername()
                );

        return LoginResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .role(account.getRole().getName())
                .firstLogin(account.getFirstLogin())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}