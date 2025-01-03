package github.com.emreisler.simplebanking.model;

import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String owner;
    private final String accountNumber;
    private double balance;
    private List<Transaction> transactions;
    private LocalDateTime createdAt;
    private Long version;

    public Account(String ownerName, String accountNumber) {
        this.owner = ownerName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.createdAt = LocalDateTime.now();
        this.transactions = new ArrayList<>();
        this.version = 1L;
    }


    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;
    }

    public void post(Transaction transaction) throws RuntimeException {
        transactions.add(transaction);
        transaction.execute(this);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
