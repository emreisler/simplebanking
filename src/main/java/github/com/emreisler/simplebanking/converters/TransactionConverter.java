package github.com.emreisler.simplebanking.converters;


import github.com.emreisler.simplebanking.dto.TransactionDto;
import github.com.emreisler.simplebanking.enums.TransactionType;
import github.com.emreisler.simplebanking.model.DepositTransaction;
import github.com.emreisler.simplebanking.model.Transaction;
import github.com.emreisler.simplebanking.model.WithdrawalTransaction;
import github.com.emreisler.simplebanking.persistence.entity.DepositTransactionEntity;
import github.com.emreisler.simplebanking.persistence.entity.TransactionEntity;
import github.com.emreisler.simplebanking.persistence.entity.WithdrawalTransactionEntity;

public class TransactionConverter {

    private TransactionConverter() {
    }

    public static Transaction toModel(TransactionEntity entity) {
        if (entity instanceof DepositTransactionEntity) {
            DepositTransactionEntity depositEntity = (DepositTransactionEntity) entity;
            DepositTransaction depositTransaction = new DepositTransaction(depositEntity.getAmount());
            depositTransaction.setDate(depositEntity.getDate());
            depositTransaction.setType(TransactionType.DEPOSIT);
            depositTransaction.setStatus(depositEntity.getStatus());
            return depositTransaction;
        } else if (entity instanceof WithdrawalTransactionEntity) {
            WithdrawalTransactionEntity withdrawalEntity = (WithdrawalTransactionEntity) entity;
            DepositTransaction depositTransaction = new DepositTransaction(withdrawalEntity.getAmount());
            depositTransaction.setDate(withdrawalEntity.getDate());
            depositTransaction.setStatus(withdrawalEntity.getStatus());
            depositTransaction.setType(TransactionType.WITHDRAWAL);
            depositTransaction.setTransactionId(withdrawalEntity.getTransactionId());
            return depositTransaction;
        } else {
            throw new IllegalArgumentException("Unsupported transaction type: " + entity.getClass());
        }
    }

    public static TransactionEntity toEntity(Transaction model) {
        if (model instanceof DepositTransaction) {
            DepositTransaction depositModel = (DepositTransaction) model;
            DepositTransactionEntity depositEntity = new DepositTransactionEntity(depositModel.getAmount());
            depositEntity.setDate(depositModel.getDate());
            depositEntity.setStatus(depositModel.getStatus());
            depositEntity.setTransactionId(depositModel.getTransactionId());
            return depositEntity;
        } else if (model instanceof WithdrawalTransaction) {
            WithdrawalTransaction withdrawalModel = (WithdrawalTransaction) model;
            WithdrawalTransactionEntity withdrawalEntity = new WithdrawalTransactionEntity(withdrawalModel.getAmount());
            withdrawalEntity.setDate(withdrawalModel.getDate());
            withdrawalEntity.setStatus(withdrawalModel.getStatus());
            withdrawalEntity.setTransactionId(withdrawalModel.getTransactionId());
            return withdrawalEntity;
        } else {
            throw new IllegalArgumentException("Unsupported transaction type: " + model.getClass());
        }
    }

    public static TransactionDto toDto(Transaction model) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(model.getAmount());
        transactionDto.setDate(model.getDate());
        transactionDto.setStatus(model.getStatus());
        transactionDto.setType(model.getType());
        transactionDto.setTransactionId(model.getTransactionId());
        return transactionDto;
    }
}