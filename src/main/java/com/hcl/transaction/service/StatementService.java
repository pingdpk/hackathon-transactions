package com.hcl.transaction.service;

import com.hcl.transaction.dto.TransactionStatementResponse;

public interface StatementService {
    TransactionStatementResponse getTransactions(Long customerId, Long accountId);
}
