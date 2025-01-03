package github.com.emreisler.simplebanking.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransactionEntity extends TransactionEntity {
    public DepositTransactionEntity(double amount) {
        super(amount);
    }

    public DepositTransactionEntity() {
        super();
    }
}
