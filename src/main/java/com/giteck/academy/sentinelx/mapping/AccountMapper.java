// src/main/java/com/giteck/academy/sentinelx/mapping/AccountMapper.java
package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.Account;
import com.giteck.academy.sentinelx.dto.AccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDTO toDto(Account entity) {
        if (entity == null) return null;
        AccountDTO dto = new AccountDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Account toEntity(AccountDTO dto) {
        if (dto == null) return null;
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}