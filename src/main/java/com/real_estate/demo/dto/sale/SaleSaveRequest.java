package com.real_estate.demo.dto.sale;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.customers.Customers;
import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import com.real_estate.demo.domain.products.Products;
import com.real_estate.demo.domain.sales.Sales;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SaleSaveRequest {
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
    private String phone; //핸드폰 포맷 010-0000-0000으로 통일하도록

    public Products toProduct(Customers customer, Accounts account){
        return Products.builder()
                .account(account)
                .name(name)
                .dong(dong)
                .hosu(hosu)
                .pyung(pyung)
                .direction(direction)
                .adExpenses(adExpenses)
                .option(option)
                .address(address)
                .customer(customer)
                .account(account)
                .build();
    }

    public Sales toSale(Products product){
        return Sales.builder()
                .registeredDate(registeredDate)
                .saleStatus(saleStatus)
                .type(type)
                .deposit(deposit)
                .rent(rent)
                .loan(loan)
                .naverNum(naverNum)
                .naverCheck(naverCheck)
                .etc(etc)
                .product(product)
                .build();
    }

    public Customers toCustomer(Accounts account){
        return Customers.builder()
                .account(account)
                .name(customerName)
                .information(information)
                .phone(phone)
                .build();
    }
}
