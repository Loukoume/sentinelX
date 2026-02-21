package com.giteck.academy.sentinelx.repository;

import com.giteck.academy.sentinelx.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransferRepository  extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification> {
}
