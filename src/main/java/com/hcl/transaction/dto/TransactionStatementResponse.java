package com.hcl.transaction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionStatementResponse {
    String comment;
    Double amount;
    String transactionId;
    String type;
    Date date;
}
