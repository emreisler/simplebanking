package github.com.emreisler.simplebanking.model;


import github.com.emreisler.simplebanking.enums.TransactionStatus;
import github.com.emreisler.simplebanking.enums.TransactionType;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;

public class WithdrawalTransaction extends Transaction {


    public WithdrawalTransaction(double amount) {
        super(amount);
        this.type = TransactionType.WITHDRAWAL;
    }

    @Override
    public void execute(Account account) {
        try {
            account.withdraw(amount);
            setStatus(TransactionStatus.SUCCESS);
        } catch (InsufficientBalanceException e) {
            status = TransactionStatus.FAILED;
            throw new InsufficientBalanceException();
        }
    }
}


