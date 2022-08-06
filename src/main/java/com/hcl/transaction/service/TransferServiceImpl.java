package com.hcl.transaction.service;

import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.entity.AccountDetails;
import com.hcl.transaction.enums.AccountStatus;
import com.hcl.transaction.enums.ApiStatus;
import com.hcl.transaction.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements  TransferService {

    private final AccountDetailsRepository accountDetailsRepository;

    public TransferResponse transferFund(Long id,TransferRequest transferRequest) {
        TransferResponse transferResponse = new TransferResponse();
        List<AccountDetails> customerDetails = accountDetailsRepository.findByCustomerId(id);
        ApiStatus apiStatus = ApiStatus.SUCCESS;
        String comment = ApiStatus.SUCCESS.name();
        if(CollectionUtils.isEmpty(customerDetails)){
           apiStatus = ApiStatus.FAILURE;
            comment = "No Source account linked with the customer id";
        }else{
            Optional<AccountDetails> optionalAccountDetails = customerDetails.stream().filter(cust -> cust.getAccountNumber().longValue() == transferRequest.getFromAccountNumber().longValue()).findAny();
            if(optionalAccountDetails.isPresent()){
                validateTransferDetails(optionalAccountDetails.get(),transferRequest, transferResponse);
                if(ApiStatus.SUCCESS.equals(transferResponse.getStatus())){
                    String transactionid = processFundTransfer(transferRequest);
                    transferResponse.setTransctionId(transactionid);
                    transferResponse.setDate(String.valueOf(LocalDate.now()));
                }
            }else{
                apiStatus = ApiStatus.FAILURE;
                comment = "Invalid Source Account details";
            }
        }

        transferResponse.setStatus(apiStatus);
        transferResponse.setComment(comment);
        return transferResponse;
    }

    @Transactional
    private String processFundTransfer(TransferRequest transferRequest) {
        AccountDetails sourceAcc = accountDetailsRepository.findByAccountNumber(transferRequest.getFromAccountNumber()).get();
        sourceAcc.setBalance(sourceAcc.getBalance() - transferRequest.getAmount());
        accountDetailsRepository.save(sourceAcc);
        AccountDetails destAcc = accountDetailsRepository.findByAccountNumber(transferRequest.getToAccountNumber()).get();
        destAcc.setBalance(destAcc.getBalance() + transferRequest.getAmount());
        accountDetailsRepository.save(destAcc);
        String transactionid = String.valueOf(UUID.randomUUID());

        //save log transaction details
        return transactionid;
    }

    private void validateTransferDetails(AccountDetails accountDetails, TransferRequest transferRequest, TransferResponse transferResponse) {

        ApiStatus apiStatus = ApiStatus.SUCCESS;
        String comment = ApiStatus.SUCCESS.name();

        if(!accountDetails.getAccountStatus().equals(AccountStatus.ACTIVE)){
            apiStatus =ApiStatus.FAILURE;
            comment = "Source account in not active";
        }
        else if(accountDetails.getBalance() < transferRequest.getAmount()){
            apiStatus = ApiStatus.FAILURE;
            comment = "Insufficient Balance";
        }else {
            Optional<AccountDetails> toAccountDetails = accountDetailsRepository.findByAccountNumber(transferRequest.getToAccountNumber());

            if (!toAccountDetails.isPresent()) {
                apiStatus = ApiStatus.FAILURE;
                comment = "Invalid destination Account";
            } else {
                if (!toAccountDetails.get().getAccountStatus().equals(AccountStatus.ACTIVE)) {
                    apiStatus =ApiStatus.FAILURE;
                    comment = "Destination account in not active";
                }
            }
        }
        transferResponse.setStatus(apiStatus);
        transferResponse.setComment(comment);
    }
}
