package github.com.emreisler.simplebanking.model;


import github.com.emreisler.simplebanking.enums.TransactionStatus;
import github.com.emreisler.simplebanking.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction {

    private UUID transactionId;
    protected double amount;
    protected TransactionStatus status;
    private LocalDateTime date;
    protected TransactionType type;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.transactionId = UUID.randomUUID();
    }

    public abstract void execute(Account account) throws RuntimeException;

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

}
