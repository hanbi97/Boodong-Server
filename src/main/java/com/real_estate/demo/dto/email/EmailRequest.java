package com.real_estate.demo.dto.email;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class EmailRequest {
    String email;
    String title;
    String message;

    @Builder
    public EmailRequest(String email, String title, String message){
        this.email=email;
        this.title=title;
        this.message = message;
    }
}
