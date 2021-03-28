package com.real_estate.demo.api;

import com.real_estate.demo.dto.customer.CustomerListResponse;
import com.real_estate.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/customer")
public class CustomerApi {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerListResponse> getCustomers(){
        return customerService.getCustomerList();
    }
}
