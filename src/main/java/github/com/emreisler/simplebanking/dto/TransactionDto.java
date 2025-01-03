package github.com.emreisler.simplebanking.dto;

import github.com.emreisler.simplebanking.enums.TransactionStatus;
import github.com.emreisler.simplebanking.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDto {
    private UUID transactionId;
    protected double amount;
    protected TransactionStatus status;
    private LocalDateTime date;
    private TransactionType type;

    public TransactionDto() {}

    public TransactionDto(UUID transactionId, double amount, TransactionStatus status, LocalDateTime date, TransactionType type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.type = type;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
