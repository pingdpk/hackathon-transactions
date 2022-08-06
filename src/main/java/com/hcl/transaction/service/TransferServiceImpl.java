package com.hcl.transaction.service;

import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.entity.AccountDetails;
import com.hcl.transaction.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements  TransferService {

    private final AccountDetailsRepository accountDetailsRepository;
    public TransferResponse transferFund(Long id,TransferRequest transferRequest) {
        List<AccountDetails> customerDeatils = accountDetailsRepository.findByCustomerId(id);
        return null;
    }
}
