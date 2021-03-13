package com.real_estate.demo.api;


import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.dto.email.EmailResponse;
import com.real_estate.demo.service.AccountsService;
import com.real_estate.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/email")
public class EmailApi {
    private final EmailService emailService;
    private final AccountsService accountsService;
    private final AccountsRepository accountsRepository;

    //사용자가 인증링크 누르면 확인
    @GetMapping("/userVerification")
    public EmailResponse acceptVerification(@RequestParam("id") Long id, @RequestParam("key") String key){
        boolean success=true;
        String msg="";
        String email="";
        try {
            Accounts account = accountsService.findOneById(id);
            accountsService.changeRole(account);
            log.info(account.getRole().name());
            msg="USER 권한 부여 성공";
            email=account.getEmail();

        }catch(NullPointerException exception){
            exception.printStackTrace();
            success=false;
            msg = "존재하지 않는 USER임";
        }

        return EmailResponse.builder()
                .success(success)
                .message(msg)
                .email(email)
                .build();
    }
}
