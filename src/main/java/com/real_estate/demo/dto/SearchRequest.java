package com.real_estate.demo.dto;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private String minRegisteredDate;
    private String maxRegisteredDate;
    private SaleStatus saleStatus;

    /**매물정보*/
    private Type type;
    private Double minDeposit;
    private Double maxDeposit;
    private Double minRent;
    private Double maxRent; //default

    /**아파트정보*/
    private String name;
    private Integer dong;
    private Integer hosu;
    private Integer minPyung;
    private Integer maxPyung;
    private String direction;

    /**고객 정보*/
    private String customerName;
    private String phone; //고객 핸드폰 뒤 4자리로 검색
}