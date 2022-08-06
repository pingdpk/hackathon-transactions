package com.hcl.transaction.service;

import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;

public interface TransferService {
    TransferResponse transferFund(Long id,TransferRequest transferRequest);
}
