package com.real_estate.demo.dto.sale;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class SaleResponse{
    private LocalDate registeredDate;
    private SaleStatus saleStatus;

    /**매물정보*/
    private Type type;
    private Double deposit;
    private Double rent; //default
    private Double loan;
    private String naverNum;
    private String naverCheck;
    private String etc;

    /**아파트정보*/
    private String name;
    private Integer dong;
    private Integer hosu;
    private Integer pyung;
    private String direction;
    private Double adExpenses;
    private String option;
    private String address;

    /**고객 정보*/
    private String customerName;
    private String information;
    private String phone;

    @Builder
    public SaleResponse(LocalDate registeredDate, SaleStatus saleStatus, Type type, Double deposit, Double rent, Double loan, String naverNum, String naverCheck, String etc, String name, Integer dong, Integer hosu, Integer pyung, String direction, Double adExpenses, String option, String address, String customerName, String information, String phone) {
        this.registeredDate = registeredDate;
        this.saleStatus = saleStatus;
        this.type = type;
        this.deposit = deposit;
        this.rent = rent;
        this.loan = loan;
        this.naverNum = naverNum;
        this.naverCheck = naverCheck;
        this.etc = etc;
        this.name = name;
        this.dong = dong;
        this.hosu = hosu;
        this.pyung = pyung;
        this.direction = direction;
        this.adExpenses = adExpenses;
        this.option = option;
        this.address = address;
        this.customerName = customerName;
        this.information = information;
        this.phone = phone;
    }
}
