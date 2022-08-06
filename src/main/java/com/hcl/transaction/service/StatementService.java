package com.hcl.transaction.service;

import java.util.List;

import com.hcl.transaction.dto.TransactionStatementResponse;

public interface StatementService {
    List<TransactionStatementResponse> getTransactions(Long customerId, Long accountId);
}
