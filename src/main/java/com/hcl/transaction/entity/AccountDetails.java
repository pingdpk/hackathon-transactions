package com.hcl.transaction.entity;

import com.hcl.transaction.enums.AccountStatus;
import com.hcl.transaction.enums.AccountType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account_detail")
@Data
public class AccountDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "account_detail_id")
    Long id;

    @Column(name = "account_number")
    Long accountNumber;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "balance")
    Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    AccountStatus accountStatus;
}
