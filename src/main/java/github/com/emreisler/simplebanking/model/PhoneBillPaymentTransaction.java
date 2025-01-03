package github.com.emreisler.simplebanking.model;

import github.com.emreisler.simplebanking.enums.TransactionStatus;
import github.com.emreisler.simplebanking.enums.TransactionType;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;

public class PhoneBillPaymentTransaction extends Transaction {

    private String company;
    private String phone;

    public PhoneBillPaymentTransaction(String company, String phone, double amount) {
        super(amount);
        this.company = company;
        this.phone = phone;
        this.status = TransactionStatus.PENDING;
        this.type = TransactionType.WITHDRAWAL;
    }

    @Override
    public void execute(Account account) throws RuntimeException {
        try {
            account.withdraw(amount);
            setStatus(TransactionStatus.SUCCESS);
        } catch (InsufficientBalanceException e) {
            status = TransactionStatus.FAILED;
            throw new InsufficientBalanceException();
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
