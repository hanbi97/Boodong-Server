package com.real_estate.demo.api;

import javax.validation.Valid;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.dto.sale.*;
import com.real_estate.demo.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/sales")
public class SaleApi {
    private final SalesService salesService;

    //전체 정보 가져오기
    @GetMapping public List<SaleListResponse> getSales(
            @ModelAttribute SearchRequest searchRequest){
        return salesService.findByParams(searchRequest);
    }

    //상세 정보 가져오기
    @GetMapping("/{id}")
    public SaleResponse getSale(@PathVariable("id") Long saleId ){
        return salesService.findById(saleId);
    }

    //새 매물 등록
    @PostMapping
    public SaleSaveResponse createProduct(@RequestBody @Valid SaleSaveRequest saleSaveRequest){
        return salesService.save(saleSaveRequest);
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
