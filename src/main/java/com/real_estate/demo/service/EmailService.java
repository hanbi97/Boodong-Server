package com.real_estate.demo.service;

import com.real_estate.demo.dto.email.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Message;
import java.io.UnsupportedEncodingException;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${base-url}") // depth가 존재하는 값은 .으로 구분해서 값을 매핑
    private String baseUrl;

    public void sendEmail(EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom("tmpacc9023@gmail.com", "부동부동 관리자");
        mimeMessageHelper.setTo(emailRequest.getEmail());
        mimeMessageHelper.setSubject(emailRequest.getTitle());
        mimeMessageHelper.setText(emailRequest.getMessage(),true);

        javaMailSender.send(mimeMessage);
    }

    public EmailRequest createUserAuthenticationMail(String email, String name, Long id){
        String key = getRandomPassword();
        return EmailRequest.builder()
                .email(email)
                .title(name+"님의 가입을 축하합니다.")
                .message("<p>안녕하세요 부동부동입니다. "+name+"님의 가입을 축하합니다. '인증 확인'을 클릭해 인증을 완료해주세요</p>"
                        +"<a href="+baseUrl+"?id="+id+"&key="+key+"> <h1> 인증 확인 </h1> </a>")
                .build();
    }

    public String getRandomPassword(){
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String str = "";
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
