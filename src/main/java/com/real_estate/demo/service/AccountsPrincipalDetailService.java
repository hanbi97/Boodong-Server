package com.real_estate.demo.service;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsPrincipalDetailService implements UserDetailsService {
    private final AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository.findByEmail(email);
        return new AccountsPrincipal(account);
    }
}
