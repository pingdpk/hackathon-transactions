package com.hcl.transaction.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.crypto.digests.NullDigest;
import org.springframework.stereotype.Service;

import com.hcl.transaction.dto.TransactionStatementResponse;
import com.hcl.transaction.entity.TransactionStatement;
import com.hcl.transaction.repository.TransactionStatementRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatementServiceImpl implements StatementService {
    private final TransactionStatementRepository transactionStatementRepository;

    @Override
    public List<TransactionStatementResponse> getTransactions(Long customerId, Long accountId) {
        List<TransactionStatement> withdrawntransactions = transactionStatementRepository.findByFromAccountId(accountId).sort((a,b) -> a.getDate() - b.getDate());;
        List<TransactionStatement> depositedTransactions = transactionStatementRepository.findByToAccountId(accountId);
        List<TransactionStatementResponse> withdrawStatements =  withdrawntransactions.stream().map(transaction -> {
            return new TransactionStatementResponse(transaction.getRemark(), 
            transaction.getAmount(), 
            transaction.getTransactionId(),
            "withdraw");
        }).collect(Collectors.toList());
        List<TransactionStatementResponse> depositeStatements =  depositedTransactions.stream().map(transaction -> {
            return new TransactionStatementResponse(transaction.getRemark(), 
            transaction.getAmount(), 
            transaction.getTransactionId(),
            "deposite");
        }).collect(Collectors.toList());

        withdrawStatements.addAll(depositeStatements)
        return ;
    }


}
