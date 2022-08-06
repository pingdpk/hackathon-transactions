package com.hcl.transaction.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class TransferRequest implements Serializable {
    Long fromAccountNumber;
    Long toAccountNumber;
    Double amount;
    Date transferDate;
    String comment;
}
