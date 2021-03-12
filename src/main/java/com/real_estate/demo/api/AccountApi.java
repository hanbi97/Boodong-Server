package com.real_estate.demo.api;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.enums.Roles;
import com.real_estate.demo.dto.account.AccountDto;
import com.real_estate.demo.dto.account.AccountSaveRequest;
import com.real_estate.demo.dto.account.AccountSaveResponse;
import com.real_estate.demo.dto.email.EmailRequest;
import com.real_estate.demo.service.AccountsService;
import com.real_estate.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/account")
public class AccountApi {
    private final AccountsService accountsService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public AccountSaveResponse saveAccount(@RequestBody @Valid AccountSaveRequest accountSaveRequest) throws MessagingException, UnsupportedEncodingException {
        AccountDto accountDto = new AccountDto(accountSaveRequest.getEmail(),accountSaveRequest.getPassword(),accountSaveRequest.getName());
        Long id = accountsService.save(accountDto);

        //이메일 인증 링크 발송
        EmailRequest emailRequest = emailService.createUserAuthenticationMail(accountSaveRequest.getEmail(), accountSaveRequest.getName(),id);
        emailService.sendEmail(emailRequest);

        return AccountSaveResponse.builder()
                    .success(true)
                    .message(accountSaveRequest.getName()+"가 가입했습니다. 인증 메일을 발송했습니다.")
                    .email(accountSaveRequest.getEmail())
                    .build();
    }
}
