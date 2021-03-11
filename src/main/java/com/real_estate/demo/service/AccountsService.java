package com.real_estate.demo.service;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.domain.enums.Roles;
import com.real_estate.demo.dto.account.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    public Long save(AccountDto accountDto){
        duplicateAccount(accountDto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        accountDto.setPassword(encoder.encode(accountDto.getPassword()));

        return accountsRepository.save(
                Accounts.builder()
                .email(accountDto.getEmail())
                .name(accountDto.getName())
                .role(Roles.NOT_PERMITTED)
                .password(accountDto.getPassword()).build()).getId();
    }

    //이미 있는 회원인지 체크
    private void duplicateAccount(String email){
        //에러 체크하는거 if else 말고 try catch로 했을때 커스텀 문구 설정 어케해야될지 모르겠음
       if(accountsRepository.findByEmail(email)!=null)
           throw new IllegalStateException("이미 가입한 회원입니다.");
    }

    //회원 찾기
    public Accounts findOneById(Long id){
        return accountsRepository.findOne(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원번호입니다."));
    }
}
