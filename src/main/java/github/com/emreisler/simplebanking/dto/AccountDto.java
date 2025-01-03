package github.com.emreisler.simplebanking.dto;


import java.time.LocalDateTime;
import java.util.List;

public class AccountDto {
    private String owner;
    private String accountNumber;
    private double balance;
    private List<TransactionDto> transactions;
    private LocalDateTime createdAt;

    public AccountDto() {
    }

    public AccountDto(String owner, String accountNumber, double balance, List<TransactionDto> transactions, LocalDateTime createdAt) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
        this.createdAt = createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
