package com.real_estate.demo.dto.customer;


import com.real_estate.demo.domain.customers.Customers;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerListResponse {
    private String name;
    private String information;
    private String phone;

    public CustomerListResponse(Customers entity){
        this.name = entity.getName();
        this.information = entity.getInformation();
        this.phone = entity.getPhone();
    }
}
