package com.osadchiy.rentservice.dao.repository;

import com.osadchiy.rentservice.dao.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);
}
