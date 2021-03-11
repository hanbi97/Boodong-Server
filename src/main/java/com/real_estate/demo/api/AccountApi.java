package com.real_estate.demo.api;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.enums.Roles;
import com.real_estate.demo.dto.account.AccountDto;
import com.real_estate.demo.dto.account.AccountSaveRequest;
import com.real_estate.demo.dto.account.AccountSaveResponse;
import com.real_estate.demo.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/account")
public class AccountApi {
    private final AccountsService accountsService;

    @PostMapping
    public AccountSaveResponse saveAccount(@RequestBody @Valid AccountSaveRequest request){
        AccountDto accountDto = new AccountDto(request.getEmail(),request.getPassword(),request.getName());
        Long id = accountsService.save(accountDto);

        //이메일 인증 링크 발송
        Accounts newAccount = accountsService.findOneById(id);
        log.info("신규가입"+newAccount.getEmail());
//        MailDto dto = mailService.createValidationEmail(newAccount.getEmail(), id, newAccount.getName());
//        mailService.sendEmail(dto);

        return AccountSaveResponse.builder()
                    .success(true)
                    .message(newAccount.getEmail()+"가입 완료, 메일 발송 완료")
                    .email(newAccount.getEmail())
                    .build();
    }
}
