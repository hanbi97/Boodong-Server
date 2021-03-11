package com.real_estate.demo.domain.sales;

import com.real_estate.demo.dto.sale.SearchRequest;

import java.util.List;

public interface SalesRepositoryCustom {
    List<Sales> findByDynamicParams(SearchRequest searchRequest);
}
