package com.real_estate.demo.domain.sales;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.real_estate.demo.dto.sale.SearchRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.real_estate.demo.domain.sales.QSales.sales;
import static org.springframework.util.StringUtils.hasLength;

@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Sales> findByDynamicParams(SearchRequest searchRequest) {
        //쪼개서 null 나오면 실행이 안됨..
        BooleanBuilder builder = new BooleanBuilder();
        /**
         * 검색 조건 - 일치or포함하는 경우
         * */
        //판매상태
        if(searchRequest.getSaleStatus() != null){
            builder.and(sales.saleStatus.eq(searchRequest.getSaleStatus()));
        }
        //매물종류
        if(searchRequest.getType() != null){
            builder.and(sales.type.eq(searchRequest.getType()));
        }
        //아파트이름
        if(searchRequest.getName() != null){
            builder.and(sales.product.name.contains(searchRequest.getName()));
        }
        //동
        if(searchRequest.getDong() != null){
            builder.and(sales.product.dong.eq(searchRequest.getDong()));
        }
        //호수수
       if(searchRequest.getHosu() != null){
            builder.and(sales.product.hosu.eq(searchRequest.getHosu()));
        }
       //고객이름
        if(searchRequest.getCustomerName() != null){
            builder.and(sales.product.customer.name.contains(searchRequest.getCustomerName()));
        }
        //고객폰
        if(searchRequest.getPhone() != null){
            builder.and(sales.product.customer.phone.contains(searchRequest.getPhone()));
        }
        /**
         * 검색 조건 - 범위검색
         * */
        if(searchRequest.getMinRegisteredDate() != null && searchRequest.getMaxRegisteredDate()!=null){
            LocalDate min = LocalDate.parse(searchRequest.getMinRegisteredDate() , DateTimeFormatter.ISO_DATE);
            LocalDate max = LocalDate.parse(searchRequest.getMaxRegisteredDate() , DateTimeFormatter.ISO_DATE);
            builder.and(sales.registeredDate.between(min, max));
        }
        if(searchRequest.getMinDeposit() != null && searchRequest.getMaxDeposit()!=null){
            builder.and(sales.deposit.between(searchRequest.getMinDeposit(), searchRequest.getMaxDeposit()));
        }
        if(searchRequest.getMinRent() != null && searchRequest.getMaxRent()!=null){
            builder.and(sales.rent.between(searchRequest.getMinRent(), searchRequest.getMaxRent()));
        }
        if(searchRequest.getMinPyung() != null && searchRequest.getMaxPyung()!=null){
            builder.and(sales.product.pyung.between(searchRequest.getMinPyung(), searchRequest.getMaxPyung()));
        }
        return jpaQueryFactory
                .selectFrom(sales)
                .where(builder)
                .fetch();
    }
}
