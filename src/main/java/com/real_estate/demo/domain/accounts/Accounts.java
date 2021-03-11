package com.real_estate.demo.domain.accounts;

import com.real_estate.demo.domain.BaseEntity;
import com.real_estate.demo.domain.enums.Roles;
import com.real_estate.demo.domain.products.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//domain+기본repo는 한묶음

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "account_id")
   private Long id;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private Roles role;

   @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Products> products = new ArrayList<>();

   @Builder
   public Accounts(String email, String name, String password, Roles role){
      this.email = email;
      this.name = name;
      this.password = password;
      this.role=role;
   }
}
