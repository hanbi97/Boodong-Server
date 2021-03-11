package com.real_estate.demo.dto.sale;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleUpdateRequest {
    /**매물 정보*/
    private SaleStatus saleStatus;
    private Type type;
    private Double deposit;
    private Double rent; //default
    private Double loan;
    private String naverNum;
    private String naverCheck;
    private String etc;

    /**아파트 정보*/
    private Double adExpenses;
    private String option;
    private String address;

}
