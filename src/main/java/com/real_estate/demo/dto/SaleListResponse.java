package com.real_estate.demo.dto;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import com.real_estate.demo.domain.products.Products;
import com.real_estate.demo.domain.sales.Sales;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SaleListResponse{
    public Long saleId;
    private LocalDate registeredDate;
    private SaleStatus saleStatus;
    private String name;
    private Integer dong;
    private Integer hosu;
    private Integer pyung;
    private String direction;
    private Type type;
    private Double deposit;
    private Double rent; //default

    public SaleListResponse(Sales entity) {
        this.saleId = entity.getId();
        this.registeredDate = entity.getRegisteredDate();
        this.saleStatus = entity.getSaleStatus();
        this.name = entity.getProduct().getName();
        this.dong = entity.getProduct().getDong();
        this.hosu = entity.getProduct().getHosu();
        this.pyung = entity.getProduct().getPyung();
        this.direction = entity.getProduct().getDirection();
        this.type = entity.getType();
        this.deposit = entity.getDeposit();
        this.rent = entity.getRent();
    }
}
