package com.real_estate.demo.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDto {
    String email;
    String name;
    String password;

    public void setPassword(String encode){
        this.password = encode;
    }
}
