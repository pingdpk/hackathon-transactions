package com.hcl.transaction.entity;

import com.hcl.transaction.enums.TransactionStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "transactions")
public class TransactionStatement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "transaction_row_id", nullable = false)
    Long transactionRowId;

    @Column(name = "transaction_id", nullable = false)
    String transactionId;

    @Column(name = "from_account", nullable= false)
    Long fromAccount;

    @Column(name = "to_account", nullable = false)
    Long toAccount;

    @Column(name = "amount", nullable = false)
    Double amount;

    @Column(name = "date", nullable = false)
    Date date;

    @Column(name = "remark", nullable = false)
    String remark;

    @Column(name = "status", nullable = false)
    TransactionStatus transactionStatus;
    
    @Column(name = "Description", nullable = false)
    String description;
}