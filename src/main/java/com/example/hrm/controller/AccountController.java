package com.example.hrm.controller;

import com.example.hrm.dto.request.ChangePasswordRequest;
import com.example.hrm.entity.Account;
import com.example.hrm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Account> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(accountService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.create(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal principal
    ) {

        accountService.changePassword(
                principal.getName(),
                request.getOldPassword(),
                request.getNewPassword()
        );

        return ResponseEntity.ok("Change password success");
    }
}