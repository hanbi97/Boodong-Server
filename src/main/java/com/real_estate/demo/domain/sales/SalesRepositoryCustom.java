package com.real_estate.demo.domain.sales;

import com.real_estate.demo.dto.SearchRequest;

import java.util.List;
import java.util.Map;

public interface SalesRepositoryCustom {
    List<Sales> findByDynamicParams(SearchRequest searchRequest);
}
