package com.real_estate.demo.api;


import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.domain.enums.Roles;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void acceptVerification(@RequestParam("id") Long id, @RequestParam("key") String key, HttpServletResponse response) throws IOException {
        boolean success=true;
        String msg="";
        String email="";
        try {
            Accounts account = accountsService.findOneById(id);
            accountsService.changeRole(account);
            if(account.getRole()== Roles.USER){
                msg="USER 권한 부여 성공";
                email=account.getEmail();
            }
            else throw new IllegalArgumentException();
        }catch(NullPointerException exception){
            exception.printStackTrace();
            success=false;
            msg = "존재하지 않는 USER임";
        }catch(IllegalArgumentException exception){
            exception.printStackTrace();
            success=false;
            msg = "권한 부여 실패";
        }//redirect
        response.sendRedirect("/");
    }
}
