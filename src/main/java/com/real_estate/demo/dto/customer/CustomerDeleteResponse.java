package com.real_estate.demo.dto.customer;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;

public class CustomerDeleteResponse extends BaseResponse {
    @Builder
    public CustomerDeleteResponse(Boolean success, String message){
        super(success, message);
    }
}