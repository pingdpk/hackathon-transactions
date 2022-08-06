package com.hcl.transaction.controller;

import com.hcl.transaction.dto.TransactionStatementResponse;
import com.hcl.transaction.service.StatementService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/statement")
@AllArgsConstructor
public class TransactionStatementController {

    private final StatementService statementService;

    @GetMapping("/{customerid}")
    public List<TransactionStatementResponse> getStatmentPerAccount(@PathVariable Long customerId, 
    @RequestParam Long accountId){
        return statementService.getTransactions(customerId, accountId);
}
}
