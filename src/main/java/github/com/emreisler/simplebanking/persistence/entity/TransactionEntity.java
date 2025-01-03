package github.com.emreisler.simplebanking.persistence.entity;

import github.com.emreisler.simplebanking.enums.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "transaction")
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID transactionId;

    private double amount;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private UUID approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    public TransactionEntity(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.approvalCode = UUID.randomUUID();
    }

    public TransactionEntity() {
        // Default constructor for JPA
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
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
}