package com.hcl.transaction.controller;

import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.service.TransferServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/transfer")
@AllArgsConstructor
public class TransferController {

    private final TransferServiceImpl transferService;
    @PostMapping("/{id}")
    public TransferResponse transferFund(@PathVariable Long id, @RequestBody TransferRequest transferRequest){
        return transferService.transferFund(id, transferRequest);
    }
}
