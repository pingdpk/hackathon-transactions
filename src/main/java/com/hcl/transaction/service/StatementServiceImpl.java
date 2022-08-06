package com.hcl.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.transaction.repository.TransactionStatementRepository;

@Service
public class StatementServiceImpl {
    private TransactionStatementRepository transactionStatementRepository;

    @Autowired
    public StatementServiceImpl(TransactionStatementRepository transactionStatementRepository) {
        this.transactionStatementRepository = transactionStatementRepository;
    }
}
