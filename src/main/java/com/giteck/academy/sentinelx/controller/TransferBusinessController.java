package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.service.impl.TransferBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/business/transfers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TransferBusinessController {

    private final TransferBusinessService transferBusinessService;

    @PostMapping("/execute")
    public String execute(@RequestParam Long fromAccountId,
                          @RequestParam Long toAccountId,
                          @RequestParam BigDecimal amount) {
        return transferBusinessService.executeTransfer(fromAccountId, toAccountId, amount);
    }
}