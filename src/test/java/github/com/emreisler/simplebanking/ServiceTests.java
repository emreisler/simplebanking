package github.com.emreisler.simplebanking;

import github.com.emreisler.simplebanking.controller.TransactionStatus;
import github.com.emreisler.simplebanking.converters.AccountConverter;
import github.com.emreisler.simplebanking.converters.TransactionConverter;
import github.com.emreisler.simplebanking.exception.AccountNotFoundException;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;
import github.com.emreisler.simplebanking.model.*;
import github.com.emreisler.simplebanking.persistence.entity.AccountEntity;
import github.com.emreisler.simplebanking.persistence.entity.TransactionEntity;
import github.com.emreisler.simplebanking.persistence.repository.AccountRepository;
import github.com.emreisler.simplebanking.persistence.repository.TransactionRepository;
import github.com.emreisler.simplebanking.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAccount_Success() {
        // Arrange
        String accountNumber = "123456";
        Account mockAccount = new Account(accountNumber, "John Doe");
        AccountEntity mockAccountEntity = AccountConverter.toEntity(mockAccount);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(mockAccountEntity));

        // Act
        Account result = accountService.findAccount(accountNumber);

        // Assert
        assertNotNull(result);
        assertEquals(mockAccount.getAccountNumber(), result.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testFindAccount_NotFound() {
        // Arrange
        String accountNumber = "123456";

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> accountService.findAccount(accountNumber));
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testPostTransaction_Success() {
        // Arrange
        String accountNumber = "123456";
        String owner = "John Doe";
        double initialBalance = 1000.0;
        double transactionAmount = 500.0;
        AccountEntity mockAccountEntity = new AccountEntity();
        mockAccountEntity.setAccountNumber(accountNumber);
        mockAccountEntity.setOwner(owner);

        Transaction mockTransaction = new DepositTransaction(transactionAmount);
        TransactionEntity mockTransactionEntity = TransactionConverter.toEntity(mockTransaction);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(mockAccountEntity));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(mockTransactionEntity);

        // Act
        TransactionStatus result = accountService.post(accountNumber, mockTransaction);

        // Assert
        assertNotNull(result);
        assertEquals(github.com.emreisler.simplebanking.enums.TransactionStatus.SUCCESS.toString(), result.getStatus());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(transactionRepository, times(2)).save(any(TransactionEntity.class)); // Initially and after status update
    }

    @Test
    void testPostTransaction_InsufficientBalance() {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            // Arrange
            String accountNumber = "123456";
            String owner = "John Doe";
            double initialBalance = 100.0;
            double transactionAmount = 500.0;
            AccountEntity mockAccountEntity = new AccountEntity();
            mockAccountEntity.setAccountNumber(accountNumber);
            mockAccountEntity.setOwner(owner);
            Transaction mockTransaction = new WithdrawalTransaction(transactionAmount);
            TransactionEntity mockTransactionEntity = TransactionConverter.toEntity(mockTransaction);

            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(mockAccountEntity));
            when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(mockTransactionEntity);

            // Act
            TransactionStatus result = accountService.post(accountNumber, mockTransaction);

            // Assert
            assertNotNull(result);
            assertEquals(github.com.emreisler.simplebanking.enums.TransactionStatus.FAILED.toString(), result.getStatus());
            verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
            verify(transactionRepository, times(2)).save(any(TransactionEntity.class)); // Initially and after status update
        });

    }

}
