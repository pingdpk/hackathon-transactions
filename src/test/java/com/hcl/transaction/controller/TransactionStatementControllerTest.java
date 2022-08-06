package com.hcl.transaction.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.*;

import com.hcl.transaction.dto.TransactionStatementResponse;
import com.hcl.transaction.service.StatementServiceImpl;

@ExtendWith(MockitoExtension.class)
// @WebMvcTest(controllers = TransactionStatementController.class)
public class TransactionStatementControllerTest {
    @InjectMocks
    TransactionStatementController controller;

    @Mock
    StatementServiceImpl serviceImpl;
    
    @Test
    public void testStatementController() {
        List<TransactionStatementResponse> result = new ArrayList<>();
        result.add(new TransactionStatementResponse("comment", 0.01, "trans123", "withdraw", new Date()));
        Mockito.when(serviceImpl.getTransactions(Mockito.anyLong(), Mockito.anyLong())).thenReturn(result);
        Assertions.assertEquals(result, controller.getStatmentPerAccount(1234L, 3456L));
    }

    // @Test

}
