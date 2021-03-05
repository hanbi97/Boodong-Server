package com.real_estate.demo.dto;

import com.real_estate.demo.domain.enums.SaleStatus;
import lombok.Builder;

public class SaleStatusChangeResponse extends BaseResponse{
    private SaleStatus saleStatus;

    @Builder
    public SaleStatusChangeResponse(Boolean success, String message, SaleStatus saleStatus){
        super(success, message);
        this.saleStatus = saleStatus;
    }
}
