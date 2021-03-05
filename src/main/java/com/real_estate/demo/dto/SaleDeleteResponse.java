package com.real_estate.demo.dto;

import lombok.Builder;

public class SaleDeleteResponse extends BaseResponse {
    @Builder
    public SaleDeleteResponse(Boolean success, String message){
        super(success, message);
    }
}
