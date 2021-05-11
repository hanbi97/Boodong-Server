package com.real_estate.demo.dto.customer;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.customers.Customers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerSaveRequest {
    /**고객 정보*/
    private String customerName;
    private String information;
    private String phone; //핸드폰 포맷 010-0000-0000으로 통일하도록

    public Customers toCustomer(Accounts account){
        return Customers.builder()
                .account(account)
                .name(customerName)
                .information(information)
                .phone(phone)
                .build();
    }
}
