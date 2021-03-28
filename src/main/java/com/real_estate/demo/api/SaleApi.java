package com.real_estate.demo.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.auth0.jwt.JWT;
import com.real_estate.demo.config.JwtProperties;
import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.dto.sale.*;
import com.real_estate.demo.service.AccountsService;
import com.real_estate.demo.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/sales")
public class SaleApi {
    private final SalesService salesService;
    private final AccountsService accountsService;

    //전체 정보 가져오기
    @GetMapping public List<SaleListResponse> getSales(
            @ModelAttribute SearchRequest searchRequest, HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        Accounts account = accountsService.findAccount(token);
        return salesService.findByParams(searchRequest,account);
    }

    //상세 정보 가져오기
    @GetMapping("/{id}")
    public SaleResponse getSale(@PathVariable("id") Long saleId, HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        Accounts account = accountsService.findAccount(token);
        return salesService.findById(saleId,account);
    }

    //새 매물 등록
    @PostMapping
    public SaleSaveResponse createProduct(@RequestBody @Valid SaleSaveRequest saleSaveRequest, HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        Accounts account = accountsService.findAccount(token);
        return salesService.save(saleSaveRequest,account);
    }

    //매물 수정
    @PutMapping("/update/{id}")
    public SaleUpdateResponse updateSale(@PathVariable Long id, @RequestBody @Valid SaleUpdateRequest saleUpdateRequest){
        return salesService.update(id,saleUpdateRequest);
    }
    @PutMapping("/{id}")
    public SaleStatusChangeResponse changeSaleStatus(@PathVariable Long id, @RequestParam(value = "saleStatus", required = false) String status){
        SaleStatus saleStatus =SaleStatus.valueOf(status);
        return salesService.changeSaleStatus(id,saleStatus);
    }

    @DeleteMapping("/{id}")
    public SaleDeleteResponse deleteSale(@PathVariable Long id){
        return salesService.delete(id);
    }

}
