package github.com.emreisler.simplebanking.model;


import github.com.emreisler.simplebanking.enums.TransactionStatus;
import github.com.emreisler.simplebanking.enums.TransactionType;

public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
        this.type = TransactionType.DEPOSIT;
    }

    @Override
    public void execute(Account account) {
        account.deposit(amount);
        setStatus(TransactionStatus.SUCCESS);
    }
}
