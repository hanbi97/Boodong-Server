package com.real_estate.demo.service;

import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.customers.Customers;
import com.real_estate.demo.domain.customers.CustomersRepository;
import com.real_estate.demo.domain.enums.Status;
import com.real_estate.demo.dto.customer.*;
import com.real_estate.demo.dto.sale.SaleUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomersRepository customersRepository;

    public List<CustomerListResponse> getCustomerList(Accounts account){
        List<CustomerListResponse> customerListResponses = new ArrayList<>();
        List<Customers> customers = customersRepository.findAll();
        for(Customers c: customers){
            if(c.getStatus()== Status.NORMAL && c.getAccount()==account) {
                customerListResponses.add(new CustomerListResponse(c));
            }
        }
        return customerListResponses;
    }

    public CustomerSaveResponse save(CustomerSaveRequest customerSaveRequest, Accounts account){
        Customers c = customersRepository.findByPhone(customerSaveRequest.getPhone());
        if(c==null){
            c= customerSaveRequest.toCustomer(account);
            customersRepository.save(c);
        }
        return CustomerSaveResponse.builder()
                .success(true)
                .message(customerSaveRequest.getCustomerName()+ "저장되었습니다.")
                .build();
    }

    public CustomerUpdateResponse update(Long customerId, CustomerUpdateRequest customerUpdateRequest){
        Customers customer = customersRepository.findById(customerId)
                .orElseThrow(()->new IllegalArgumentException(("해당 손님은 없습니다. id= "+customerId)));

        customer.update(customerUpdateRequest.getCustomerName(), customerUpdateRequest.getInformation(), customerUpdateRequest.getPhone());
        customersRepository.save(customer);
        return CustomerUpdateResponse.builder()
                .success(true)
                .message("수정했습니다.")
                .id(customerId)
                .build();
    }

    public CustomerDeleteResponse delete(Long customerId){
        Customers customer = customersRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("해당 손님은 없습니다. id="+customerId));
        customer.softDelete();
        customersRepository.save(customer);

        return CustomerDeleteResponse.builder()
                .success(true)
                .message("지워졌습니다.")
                .build();
    }
}
