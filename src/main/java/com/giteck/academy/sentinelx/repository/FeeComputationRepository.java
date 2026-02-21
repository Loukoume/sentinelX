package com.giteck.academy.sentinelx.repository;

import com.giteck.academy.sentinelx.entity.FeeComputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeComputationRepository extends JpaRepository<FeeComputation, Long>, JpaSpecificationExecutor<FeeComputation> {}