package com.real_estate.demo.service;

import com.auth0.jwt.JWT;
import com.real_estate.demo.config.JwtProperties;
import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.domain.enums.Roles;
import com.real_estate.demo.dto.account.AccountSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    public Long save(AccountSaveRequest accountSaveRequest){
        duplicateAccount(accountSaveRequest.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        accountSaveRequest.setPassword(encoder.encode(accountSaveRequest.getPassword()));

        return accountsRepository.save(
                Accounts.builder()
                .email(accountSaveRequest.getEmail())
                .name(accountSaveRequest.getName())
                .role(Roles.NOT_PERMITTED)
                .password(accountSaveRequest.getPassword()).build()).getId();
    }

    public void changeRole(Accounts account){
        account.changeRole(Roles.USER);
        accountsRepository.save(account);
    }

    //이미 있는 회원인지 체크
    private void duplicateAccount(String email){
        //에러 체크하는거 if else 말고 try catch로 했을때 커스텀 문구 설정 어케해야될지 모르겠음
       if(accountsRepository.findByEmail(email)!=null)
           throw new IllegalStateException("이미 가입한 회원입니다.");
    }

    //회원 찾기
    public Accounts findOneById(Long id){
        return accountsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원번호입니다."));
    }

    public Accounts findAccount(String token){
        String username= JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
        return accountsRepository.findByEmail(username);
    }
}
