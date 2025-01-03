package github.com.emreisler.simplebanking;

import github.com.emreisler.simplebanking.controller.AccountController;
import github.com.emreisler.simplebanking.controller.TransactionStatus;
import github.com.emreisler.simplebanking.dto.AccountDto;
import github.com.emreisler.simplebanking.dto.AmountDto;
import github.com.emreisler.simplebanking.model.Account;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;
import github.com.emreisler.simplebanking.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @Mock
    private AccountService service;

    
    @Test
    public void givenId_Credit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new AmountDto(1000.0));
        verify(service, times(1)).findAccount("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new AmountDto(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new AmountDto(50.0));
        verify(service, times(2)).findAccount("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.OK, result2.getStatusCode());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<TransactionStatus> result = controller.credit( "17892", new AmountDto(1000.0));
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new AmountDto(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<AccountDto> result = controller.getAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }

}
