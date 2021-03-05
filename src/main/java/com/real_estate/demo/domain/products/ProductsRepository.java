package com.real_estate.demo.domain.products;

import com.real_estate.demo.domain.sales.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsRepository extends JpaRepository<Products,Long>,JpaSpecificationExecutor<Products>{
    Products findByKey(String key);
}
