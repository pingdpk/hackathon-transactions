package com.hcl.transaction.service;


import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.entity.AccountDetails;
import com.hcl.transaction.entity.TransactionStatement;
import com.hcl.transaction.enums.AccountStatus;
import com.hcl.transaction.enums.AccountType;
import com.hcl.transaction.enums.ApiStatus;
import com.hcl.transaction.repository.AccountDetailsRepository;
import com.hcl.transaction.repository.TransactionStatementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {

    @InjectMocks
    TransferServiceImpl transferService;

    @Mock
    AccountDetailsRepository accountDetailsRepository;

    @Mock
    TransactionStatementRepository transactionStatementRepository;

    @Test
    public void fundTransferNoSourceAccount(){
        TransferRequest accountRequest = getTransferAccountRequest();
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Collections.emptyList());
        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.FAILURE, transferResponse.getStatus());
        Assertions.assertEquals("No Source account linked with the customer id",transferResponse.getComment());
        Assertions.assertNull(transferResponse.getTransctionId());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
    }

    @Test
    public void fundTransferSuspendSourceAccount(){
        TransferRequest accountRequest = getTransferAccountRequest();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountStatus(AccountStatus.SUSPEND);
        accountDetails.setAccountNumber(98756L);
        accountDetails.setCustomerId(1L);
        accountDetails.setAccountType(AccountType.CURRENT);
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Arrays.asList(accountDetails));
        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.FAILURE, transferResponse.getStatus());
        Assertions.assertEquals("Source account in not active",transferResponse.getComment());
        Assertions.assertNull(transferResponse.getTransctionId());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
    }

    @Test
    public void fundTransferInvalidSourceAccountBalance(){
        TransferRequest accountRequest = getTransferAccountRequest();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountStatus(AccountStatus.ACTIVE);
        accountDetails.setAccountNumber(98756L);
        accountDetails.setCustomerId(1L);
        accountDetails.setAccountType(AccountType.CURRENT);
        accountDetails.setBalance(100D);
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Arrays.asList(accountDetails));
        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.FAILURE, transferResponse.getStatus());
        Assertions.assertEquals("Insufficient Balance",transferResponse.getComment());
        Assertions.assertNull(transferResponse.getTransctionId());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
    }

    @Test
    public void fundTransferInvalidDestAccount(){
        TransferRequest accountRequest = getTransferAccountRequest();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountStatus(AccountStatus.ACTIVE);
        accountDetails.setAccountNumber(98756L);
        accountDetails.setCustomerId(1L);
        accountDetails.setAccountType(AccountType.CURRENT);
        accountDetails.setBalance(1234567D);
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Arrays.asList(accountDetails));
        Mockito.when(accountDetailsRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(Optional.empty());
        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.FAILURE, transferResponse.getStatus());
        Assertions.assertEquals("Invalid destination Account",transferResponse.getComment());
        Assertions.assertNull(transferResponse.getTransctionId());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByAccountNumber(Mockito.anyLong());
    }

    @Test
    public void fundTransferDestAccountNotActive(){
        TransferRequest accountRequest = getTransferAccountRequest();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountStatus(AccountStatus.ACTIVE);
        accountDetails.setAccountNumber(98756L);
        accountDetails.setCustomerId(1L);
        accountDetails.setAccountType(AccountType.CURRENT);
        accountDetails.setBalance(1234567D);
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Arrays.asList(accountDetails));

        AccountDetails destAccountDetails = new AccountDetails();
        destAccountDetails.setAccountStatus(AccountStatus.SUSPEND);
        destAccountDetails.setAccountNumber(123456L);
        destAccountDetails.setAccountType(AccountType.CURRENT);
        destAccountDetails.setBalance(1234D);
        Mockito.when(accountDetailsRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(Optional.of(destAccountDetails));

        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.FAILURE, transferResponse.getStatus());
        Assertions.assertEquals("Destination account in not active",transferResponse.getComment());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByAccountNumber(Mockito.anyLong());
    }


    @Test
    public void fundTransferSuccess(){
        TransferRequest accountRequest = getTransferAccountRequest();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountStatus(AccountStatus.ACTIVE);
        accountDetails.setAccountNumber(98756L);
        accountDetails.setCustomerId(1L);
        accountDetails.setAccountType(AccountType.CURRENT);
        accountDetails.setBalance(1234567D);
        Mockito.when(accountDetailsRepository.findByCustomerId(Mockito.anyLong())).thenReturn(Arrays.asList(accountDetails));

        AccountDetails destAccountDetails = new AccountDetails();
        destAccountDetails.setAccountStatus(AccountStatus.ACTIVE);
        destAccountDetails.setAccountNumber(123456L);
        destAccountDetails.setAccountType(AccountType.CURRENT);
        destAccountDetails.setBalance(1234D);
        Mockito.when(accountDetailsRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(Optional.of(destAccountDetails));

        TransferResponse transferResponse = transferService.transferFund(1L, accountRequest);
        Assertions.assertNotNull(transferResponse);
        Assertions.assertEquals(ApiStatus.SUCCESS, transferResponse.getStatus());
        Assertions.assertNotNull(transferResponse.getTransctionId());
        Mockito.verify(accountDetailsRepository,Mockito.times(1)).findByCustomerId(Mockito.anyLong());
        Mockito.verify(accountDetailsRepository,Mockito.times(3)).findByAccountNumber(Mockito.anyLong());
        Mockito.verify(accountDetailsRepository,Mockito.times(2)).save(Mockito.any(AccountDetails.class));
        Mockito.verify(transactionStatementRepository,Mockito.times(1)).save(Mockito.any(TransactionStatement.class));
    }
    private TransferRequest getTransferAccountRequest() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setToAccountNumber(123456L);
        transferRequest.setFromAccountNumber(98756L);
        transferRequest.setComment("transfer money");
        transferRequest.setTransferDate(java.sql.Date.valueOf(LocalDate.now()));
        transferRequest.setAmount(123456D);
        return transferRequest;
    }

}