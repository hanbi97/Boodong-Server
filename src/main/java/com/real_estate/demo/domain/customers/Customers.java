package com.real_estate.demo.domain.customers;

import com.real_estate.demo.domain.BaseEntity;
import com.real_estate.demo.domain.products.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customers extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String information;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();

    @Builder
    public Customers(String name, String information, String phone ) {
        this.name=name;
        this.information=information;
        this.phone=phone;
    }
}

