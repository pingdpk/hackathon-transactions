package com.hcl.transaction.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionStatementResponse {
    String statement;
    String comment;
    Double amount;
    String transactionId;
}
