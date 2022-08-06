package com.hcl.transaction.controller;

import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.enums.ApiStatus;
import com.hcl.transaction.service.TransferServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransferControllerTest {

    @InjectMocks
    TransferController transferController;

    @Mock
    TransferServiceImpl transferService;

    @Test
    public void transferFund(){
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(123456D);
        transferRequest.setFromAccountNumber(123456L);
        transferRequest.setToAccountNumber(98766L);
        transferRequest.setComment("transfer-comment");
        TransferResponse mckresponse = Mockito.mock(TransferResponse.class);
        Mockito.when(mckresponse.getComment()).thenReturn("success transfer");
        Mockito.when(mckresponse.getStatus()).thenReturn(ApiStatus.SUCCESS);
        Mockito.when(transferService.transferFund(Mockito.anyLong(),Mockito.any(TransferRequest.class))).thenReturn(mckresponse);
        TransferResponse methResponse = transferController.transferFund(1L, transferRequest);
        Assertions.assertNotNull(methResponse);
        Assertions.assertEquals("success transfer",methResponse.getComment());
        Assertions.assertEquals(ApiStatus.SUCCESS, methResponse.getStatus());

    }
}