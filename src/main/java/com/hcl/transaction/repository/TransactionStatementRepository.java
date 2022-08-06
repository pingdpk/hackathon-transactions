package com.hcl.transaction.repository;

import com.hcl.transaction.entity.TransactionStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionStatementRepository extends JpaRepository<TransactionStatement, String> {
<<<<<<< HEAD
    /*@Query("select * from TransactionStatement where fromAccountId = ? union select * from TransactionStatement where toAccountId = ?'")
    List<TransactionStatement> findByAccountId(Long accountId);
    List<TransactionStatement> findByFromAccountId(Long accountId);
    List<TransactionStatement> findByToAccountId(Long accountId);*/
=======
    // @Query("select * from TransactionStatement where fromAccountId = ? union select * from TransactionStatement where toAccountId = ?'")
    // List<TransactionStatement> findByAccount(Long accountId);
    List<TransactionStatement> findByFromAccount(Long accountId);
    List<TransactionStatement> findByToAccount(Long accountId);
>>>>>>> ts2
}
