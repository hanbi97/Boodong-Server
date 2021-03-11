package com.real_estate.demo.dto.sale;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;

public class SaleDeleteResponse extends BaseResponse {
    @Builder
    public SaleDeleteResponse(Boolean success, String message){
        super(success, message);
    }
}
