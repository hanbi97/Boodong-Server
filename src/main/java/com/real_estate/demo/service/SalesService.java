package com.real_estate.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.real_estate.demo.domain.customers.Customers;
import com.real_estate.demo.domain.customers.CustomersRepository;
import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Status;
import com.real_estate.demo.domain.products.Products;
import com.real_estate.demo.domain.products.ProductsRepository;
import com.real_estate.demo.domain.sales.Sales;
import com.real_estate.demo.domain.sales.SalesRepository;
import com.real_estate.demo.dto.sale.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SalesService {
    private final ProductsRepository productsRepository;
    private final CustomersRepository customersRepository;
    private final SalesRepository salesRepository;

    @Transactional(readOnly = true)
    public List<SaleListResponse> findByParams(SearchRequest searchRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> tmp = objectMapper.convertValue(searchRequest,Map.class);
        boolean check=false;
        for (Map.Entry<String,Object> entry : tmp.entrySet()){
            if(entry.getValue()!=null){
                check=true;
                break;
            }
        }
       List<Sales> sales = check? salesRepository.findByDynamicParams(searchRequest):salesRepository.findAll();

        List<SaleListResponse> res = new ArrayList<>();
        for(Sales s : sales){
            if(s.getStatus()==Status.NORMAL){
                res.add(new SaleListResponse(s));
            }
        }
        return res;
    }

    @Transactional(readOnly = true)
    public SaleResponse findById(Long saleId){
        Sales s = salesRepository.findById(saleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물은 없습니다. id="+saleId));
        if(s.getStatus()==Status.DELETE)
            throw new IllegalArgumentException("해당 매물은 없습니다. id="+saleId);
        return SaleResponse.builder()
                .registeredDate(s.getRegisteredDate())
                .saleStatus(s.getSaleStatus())
                .type(s.getType())
                .deposit(s.getDeposit())
                .rent(s.getRent())
                .loan(s.getLoan())
                .naverNum(s.getNaverNum())
                .naverCheck(s.getNaverCheck())
                .etc(s.getEtc())
                .name(s.getProduct().getName())
                .dong(s.getProduct().getDong())
                .hosu(s.getProduct().getHosu())
                .pyung(s.getProduct().getPyung())
                .direction(s.getProduct().getDirection())
                .adExpenses(s.getProduct().getAdExpenses())
                .option(s.getProduct().getOption())
                .address(s.getProduct().getAddress())
                .customerName(s.getProduct().getCustomer().getName())
                .information(s.getProduct().getCustomer().getInformation())
                .phone(s.getProduct().getCustomer().getPhone())
                .build();
    }

    public SaleSaveResponse save(SaleSaveRequest saleSaveRequest){
        //손님 없으면 저장
        Customers c = customersRepository.findByPhone(saleSaveRequest.getPhone());
        if(c==null){
            c= saleSaveRequest.toCustomer();
            customersRepository.save(c);
        }

        //아파트 없으면 저장
        String productKey = saleSaveRequest.getName()+saleSaveRequest.getDong()+saleSaveRequest.getHosu();
        Products product = productsRepository.findByKey(productKey);
        if(product==null){
            product = saleSaveRequest.toProduct(c,null);
            productsRepository.save(product);
        }

        Sales sale = saleSaveRequest.toSale(product);
        String saleKey= product.getKey()+sale.getType();
        salesRepository.save(sale);

        return SaleSaveResponse.builder()
                .success(true)
                .message("저장되었습니다.")
                .saleKey(saleKey)
                .build();
    }

    public SaleUpdateResponse update(Long saleId, SaleUpdateRequest saleUpdateRequest){
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(()->new IllegalArgumentException(("해당 매물은 없습니다. id= "+saleId)));

        Products products = sales.getProduct();
        products.update(saleUpdateRequest.getAdExpenses(), saleUpdateRequest.getOption(),saleUpdateRequest.getAddress());
        sales.update(saleUpdateRequest.getSaleStatus(), saleUpdateRequest.getType(),saleUpdateRequest.getDeposit(),
                        saleUpdateRequest.getRent(),saleUpdateRequest.getLoan(),saleUpdateRequest.getNaverNum(),saleUpdateRequest.getNaverCheck(),saleUpdateRequest.getEtc(), products);

        salesRepository.save(sales);
        return SaleUpdateResponse.builder()
                .success(true)
                .message("수정했습니다.")
                .id(saleId)
                .build();
    }

    public SaleDeleteResponse delete(Long saleId){
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물은 없습니다. id="+saleId));
        sales.softDelete();
        salesRepository.save(sales);

        return SaleDeleteResponse.builder()
                .success(true)
                .message("지워졌습니다.")
                .build();
    }

    public SaleStatusChangeResponse changeSaleStatus(Long saleId, SaleStatus saleStatus){
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물은 없습니다. id="+saleId));
        sales.changeStatus(saleStatus);
        salesRepository.save(sales);

        return SaleStatusChangeResponse.builder()
                .success(true)
                .message("변경되었습니다.")
                .saleStatus(saleStatus)
                .build();
    }
}
