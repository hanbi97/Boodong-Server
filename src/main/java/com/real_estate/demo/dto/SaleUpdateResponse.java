package com.real_estate.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleUpdateResponse extends BaseResponse{
    private Long id;
    @Builder
    public SaleUpdateResponse(Boolean success, String message, Long id){
        super(success, message);
        this.id = id;
    }
}
