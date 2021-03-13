package com.real_estate.demo.dto.account;

import com.real_estate.demo.domain.accounts.Accounts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSaveRequest {
    String email;
    String name;
    String password;

    public void setPassword(String encode){
        this.password = encode;
    }
}
