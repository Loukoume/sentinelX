// src/main/java/com/giteck/academy/sentinelx/repository/RetryAttemptRepository.java
package com.giteck.academy.sentinelx.repository;

import com.giteck.academy.sentinelx.entity.RetryAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RetryAttemptRepository extends JpaRepository<RetryAttempt, Long>, JpaSpecificationExecutor<RetryAttempt> {}