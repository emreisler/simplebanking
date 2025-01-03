package github.com.emreisler.simplebanking.services;


import github.com.emreisler.simplebanking.controller.TransactionStatus;
import github.com.emreisler.simplebanking.converters.AccountConverter;
import github.com.emreisler.simplebanking.converters.TransactionConverter;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;
import github.com.emreisler.simplebanking.model.*;
import github.com.emreisler.simplebanking.exception.AccountNotFoundException;
import github.com.emreisler.simplebanking.persistence.entity.AccountEntity;
import github.com.emreisler.simplebanking.persistence.entity.TransactionEntity;
import github.com.emreisler.simplebanking.persistence.repository.AccountRepository;
import github.com.emreisler.simplebanking.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account findAccount(String accountNumber) {
        return accountRepository
                .findByAccountNumber(accountNumber)
                .map(AccountConverter::toModel)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }


    @Transactional
    public TransactionStatus post(String accountNumber, Transaction transaction) throws RuntimeException {
        AccountEntity accountEntity = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        TransactionEntity transactionEntity = TransactionConverter.toEntity(transaction);
        transactionEntity.setAccount(accountEntity);

        TransactionEntity persistedTransaction = transactionRepository.save(transactionEntity);

        Account account = AccountConverter.toModel(accountEntity);


        account.post(transaction);
        AccountEntity updatedAccountEntity = AccountConverter.toEntity(account);
        updatedAccountEntity.setId(accountEntity.getId());
        accountRepository.save(updatedAccountEntity);
        persistedTransaction.setStatus(github.com.emreisler.simplebanking.enums.TransactionStatus.SUCCESS);
        transactionRepository.save(persistedTransaction);

        TransactionStatus transactionStatus = new TransactionStatus();
        transactionStatus.setStatus(persistedTransaction.getStatus().toString());
        transactionStatus.setApprovalCode(persistedTransaction.getApprovalCode());
        return transactionStatus;

    }

}
