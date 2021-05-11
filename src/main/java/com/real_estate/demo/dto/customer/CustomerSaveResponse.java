package com.real_estate.demo.dto.customer;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;

public class CustomerSaveResponse extends BaseResponse {
    @Builder
    public CustomerSaveResponse(Boolean success, String message){
        super(success, message);
    }
}
