package com.example.hrm.config;


import com.example.hrm.entity.Account;
import com.example.hrm.repository.AccountRepository;
import com.example.hrm.security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final AccountRepository accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        Account account = accountRepository
//                .findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found"));
//
//        return new CustomUserPrincipal(account);
//    }
//}