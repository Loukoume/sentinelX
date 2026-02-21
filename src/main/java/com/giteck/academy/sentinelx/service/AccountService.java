package com.giteck.academy.sentinelx.service;

import com.giteck.academy.sentinelx.dto.AccountDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    AccountDTO save(AccountDTO dto);

    AccountDTO update(AccountDTO dto);

    AccountDTO findById(Long id);

    List<AccountDTO> findAll();

    void deleteById(Long id);
}
