package com.real_estate.demo.dto.customer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerUpdateRequest {
    private String customerName;
    private String information;
    private String phone; //핸드폰 포맷 010-0000-0000으로 통일하도록
}
