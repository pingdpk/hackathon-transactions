package com.hcl.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hcl.transaction.entity.TransactionStatement;

@Repository
public interface TransactionStatementRepository extends JpaRepository<TransactionStatement, String> {
    @Query("select * from TransactionStatement where fromAccountId = ? union select * from TransactionStatement where toAccountId = ?'")
    List<TransactionStatement> findByAccountId(Long accountId);
    List<TransactionStatement> findByFromAccountId(Long accountId);
    List<TransactionStatement> findByToAccountId(Long accountId);
}
