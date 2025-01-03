package github.com.emreisler.simplebanking.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransactionEntity extends TransactionEntity {

    public WithdrawalTransactionEntity(double amount) {
        super(amount);
    }

    public WithdrawalTransactionEntity() {
        super();
    }
}
