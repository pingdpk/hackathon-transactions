import com.hcl.transaction.dto.TransactionStatementResponse;
import com.hcl.transaction.dto.TransferRequest;
import com.hcl.transaction.dto.TransferResponse;
import com.hcl.transaction.service.StatementService;
import com.hcl.transaction.service.TransferServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.beans.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/statement")
@AllArgsConstructor
public class TransactionStatementController {

    private final StatementService statementService;

    @GetMapping("/{customerid}")
    public List<TransactionStatementResponse> getStatmentPerAccount(@PathVariable Long customerId, 
    @RequestParam Long accountId){
        return statementService.getTransactions(customerId, accountId);
}
}
