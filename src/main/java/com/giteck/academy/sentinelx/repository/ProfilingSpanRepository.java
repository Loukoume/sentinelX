// src/main/java/com/giteck/academy/sentinelx/repository/ProfilingSpanRepository.java
package com.giteck.academy.sentinelx.repository;

import com.giteck.academy.sentinelx.entity.ProfilingSpan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilingSpanRepository extends JpaRepository<ProfilingSpan, String>, JpaSpecificationExecutor<ProfilingSpan> {}