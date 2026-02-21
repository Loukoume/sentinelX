// src/main/java/com/giteck/academy/sentinelx/repository/AccountRepository.java
package com.giteck.academy.sentinelx.repository;

import com.giteck.academy.sentinelx.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByAccountNumber(String accountNumber);
}