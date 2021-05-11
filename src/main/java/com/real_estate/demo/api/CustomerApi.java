package com.real_estate.demo.api;

import com.real_estate.demo.config.JwtProperties;
import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.dto.customer.*;
import com.real_estate.demo.dto.sale.*;
import com.real_estate.demo.service.AccountsService;
import com.real_estate.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/customer")
public class CustomerApi {
    private final CustomerService customerService;
    private final AccountsService accountsService;

    @GetMapping
    public List<CustomerListResponse> getCustomers(HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        Accounts account = accountsService.findAccount(token);
        return customerService.getCustomerList(account);
    }

    //손님등록
    @PostMapping
    public CustomerSaveResponse createCustomer(@RequestBody @Valid CustomerSaveRequest customerSaveRequest, HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        Accounts account = accountsService.findAccount(token);
        return customerService.save(customerSaveRequest,account);
    }

    //손님정보 수정
    @PutMapping("/update/{id}")
    public CustomerUpdateResponse updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerUpdateRequest customerUpdateRequest){
        return customerService.update(id,customerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public CustomerDeleteResponse deleteCustomer(@PathVariable Long id){
        return customerService.delete(id);
    }
}
