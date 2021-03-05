package com.real_estate.demo.domain.sales;

import com.real_estate.demo.domain.BaseEntity;
import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Status;
import com.real_estate.demo.domain.enums.Type;
import com.real_estate.demo.domain.products.Products;
import com.real_estate.demo.dto.SaleUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Sales extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    @Column(name="sale_key", nullable = false)
    private String key;

    @Column(nullable = false)
    private LocalDate registeredDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus saleStatus;

    /**매매전세월세*/
    @Enumerated(EnumType.STRING)
    private Type type;

    private Double deposit; //보증금 있는 경우만 넣음

    @Column(nullable = false)
    private Double rent; //default

    private Double loan;

    /**네이버 등록*/
    private String naverNum;
    private String naverCheck;

    private String etc;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Products product; //fk

    @Builder
    public Sales(LocalDate registeredDate, SaleStatus saleStatus, Type type, Double deposit, Double rent,
                 Double loan, String naverNum, String naverCheck, String etc, Products product) {
        this.key = product.getKey()+type;
        this.registeredDate = registeredDate;
        this.saleStatus  = saleStatus;
        this.type = type;
        this.deposit = deposit;
        this.rent = rent;
        this.loan = loan;
        this.naverNum = naverNum;
        this.naverCheck = naverCheck;
        this.etc = etc;
        this.product = product;
    }


    /**business method*/
    public void update(SaleStatus saleStatus, Type type, Double deposit, Double rent, Double loan,
                       String naverNum, String naverCheck, String etc, Products product) {
        this.saleStatus  = saleStatus;
        this.type = type;
        this.deposit = deposit;
        this.rent = rent;
        this.loan = loan;
        this.naverNum = naverNum;
        this.naverCheck = naverCheck;
        this.etc = etc;
        this.product = product;
    }

    public void changeStatus(SaleStatus saleStatus){
        this.saleStatus = saleStatus;
    }
}
