package com.real_estate.demo.service;

import com.real_estate.demo.domain.customers.Customers;
import com.real_estate.demo.domain.customers.CustomersRepository;
import com.real_estate.demo.domain.enums.Status;
import com.real_estate.demo.dto.customer.CustomerListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomersRepository customersRepository;

    public List<CustomerListResponse> getCustomerList(){
        List<CustomerListResponse> customerListResponses = new ArrayList<>();
        List<Customers> customers = customersRepository.findAll();
        for(Customers c: customers){
            if(c.getStatus()== Status.DELETE) continue;
            customerListResponses.add(new CustomerListResponse(c));
        }
        return customerListResponses;
    }
}
