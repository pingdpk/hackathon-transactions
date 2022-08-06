package com.hcl.transaction.repository;

import com.hcl.transaction.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
    List<AccountDetails> findByCustomerId(Long id);

    Optional<AccountDetails> findByAccountNumber(Long toAccountNumber);
}
