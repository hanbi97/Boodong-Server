package com.real_estate.demo.domain.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Accounts findByEmail(String email); //이미 가입한 회원인지 확인
    Accounts findByName(String name);
}
