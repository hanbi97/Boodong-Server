package com.real_estate.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleSaveResponse extends BaseResponse{
    private String saleKey;

    @Builder
    public SaleSaveResponse(Boolean success, String message, String saleKey){
        super(success, message);
        this.saleKey = saleKey;
    }
}
