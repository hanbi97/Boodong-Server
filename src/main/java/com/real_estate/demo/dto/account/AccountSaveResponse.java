package com.real_estate.demo.dto.account;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSaveResponse extends BaseResponse {
    String email;

    @Builder
    public AccountSaveResponse(Boolean success, String message,String email){
        super(success, message);
        this.email=email;
    }
}
