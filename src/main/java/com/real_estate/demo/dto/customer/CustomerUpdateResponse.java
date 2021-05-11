package com.real_estate.demo.dto.customer;

import com.real_estate.demo.dto.BaseResponse;
import lombok.Builder;

public class CustomerUpdateResponse extends BaseResponse {
    private Long id;
    @Builder
    public CustomerUpdateResponse(Boolean success, String message, Long id){
        super(success, message);
        this.id = id;
    }
}
