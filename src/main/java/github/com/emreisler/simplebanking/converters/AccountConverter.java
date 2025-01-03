package github.com.emreisler.simplebanking.converters;

import github.com.emreisler.simplebanking.dto.AccountDto;
import github.com.emreisler.simplebanking.dto.TransactionDto;
import github.com.emreisler.simplebanking.model.Account;
import github.com.emreisler.simplebanking.model.Transaction;
import github.com.emreisler.simplebanking.persistence.entity.AccountEntity;
import github.com.emreisler.simplebanking.persistence.entity.TransactionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AccountConverter {

    private AccountConverter() {
    }

    public static Account toModel(AccountEntity entity) {
        Account account = new Account(entity.getOwner(), entity.getAccountNumber());
        account.setBalance(entity.getBalance());
        account.setCreatedAt(entity.getCreatedAt());
        if (entity.getTransactionEntities() != null) {
            List<Transaction> transactions = entity.getTransactions().stream()
                    .map(TransactionConverter::toModel)
                    .collect(Collectors.toList());
            account.setTransactions(transactions);
        }
        account.setVersion(entity.getVersion());
        return account;
    }

    public static AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(account.getAccountNumber());
        entity.setOwner(account.getOwner());
        entity.setBalance(account.getBalance());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setAccountNumber(account.getAccountNumber());
        if (account.getTransactions() != null) {
            List<TransactionEntity> transactionEntities = account.getTransactions().stream()
                    .map(TransactionConverter::toEntity)
                    .collect(Collectors.toList());
            entity.setTransactionEntities(transactionEntities);
        }
        entity.setVersion(account.getVersion());

        return entity;
    }

    public static AccountDto toDto(Account model) {
        AccountDto dto = new AccountDto();
        dto.setAccountNumber(model.getAccountNumber());
        dto.setOwner(model.getOwner());
        dto.setBalance(model.getBalance());
        dto.setCreatedAt(model.getCreatedAt());
        List<TransactionDto> transactionDtos = model.getTransactions().stream()
                .map(TransactionConverter::toDto)
                .collect(Collectors.toList());
        dto.setTransactions(transactionDtos);
        return dto;

    }
}
