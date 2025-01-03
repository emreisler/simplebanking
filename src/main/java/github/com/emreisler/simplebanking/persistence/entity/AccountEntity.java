package github.com.emreisler.simplebanking.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @OneToMany(mappedBy = "account", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<TransactionEntity> transactionEntities;

    @Version
    private Long version;

    private double balance;

    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<TransactionEntity> getTransactions() {
        return transactionEntities;
    }

    public void setTransactions(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
