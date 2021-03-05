package com.real_estate.demo.domain.products;

import com.real_estate.demo.domain.BaseEntity;
import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.customers.Customers;
import com.real_estate.demo.domain.sales.Sales;
import com.real_estate.demo.dto.SaleUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name="product_key", nullable = false)
    private String key;

    /**아파트정보*/
    @Column(nullable = false)
    private String name;

    private Integer dong;

    private Integer hosu;

    @Column(nullable = false)
    private Integer pyung;

    @Column(nullable = false)
    private String direction;

    private Double adExpenses;

    @Column(name="product_option")
    private String option;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Accounts account; //fk

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customers customer; //fk

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sales> sales = new ArrayList<>();

    @Builder //귀찮지만 명확하게 하기 위해..
    public Products(String name, Integer dong, Integer hosu, Integer pyung,
                    String direction, Double adExpenses, String option, String address,
                    Accounts account, Customers customer) {
        this.key = name+dong+hosu;
        this.name = name;
        this.dong = dong;
        this.hosu = hosu;
        this.pyung = pyung;
        this.direction = direction;
        this.adExpenses = adExpenses;
        this.option = option;
        this.address = address;
        this.account = account;
        this.customer = customer;
    }

    //관리비, 옵션, 주소(행정구역 변경시)만 변경가능
    public void update(Double adExpenses, String option, String address){
        this.adExpenses = adExpenses;
        this.option = option;
        this.address = address;
    }
}
