package com.hcl.transaction.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransferRequest implements Serializable {
    Long fromAccountNumber;
    Long toAccountNumber;
    Double amount;
    String transferDate;
    String comment;
}
