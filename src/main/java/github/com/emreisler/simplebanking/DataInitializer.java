package github.com.emreisler.simplebanking;

import github.com.emreisler.simplebanking.persistence.repository.AccountRepository;
import github.com.emreisler.simplebanking.persistence.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionServiceFactory;

    public DataInitializer( AccountRepository accountRepository, TransactionRepository transactionServiceFactory) {
        this.accountRepository = accountRepository;
        this.transactionServiceFactory = transactionServiceFactory;
    }

    @Override
    public void run(String... args) throws Exception {


        // Create accounts
//        Account account1 = new Account("Kerem Karaca", "669-7788");
//        Account account2 = new Account("John Doe", "123-4567");
//
//        // Add initial transactions
//        account1.post(new DepositTransaction(1000));
//        account1.post(new WithdrawalTransaction(200));
//
//        account2.post(new DepositTransaction(500));
//        account2.post(new WithdrawalTransaction(300));
//
//        // Save accounts to the repository
//        accountRepository.save(AccountConverter.toEntity(account1));
//        accountRepository.save(AccountConverter.toEntity(account2));
//
//        System.out.println("Initialized accounts:");
//        System.out.println("Account 1: " + account1.getAccountNumber() + ", Balance: " + account1.getBalance());
//        System.out.println("Account 2: " + account2.getAccountNumber() + ", Balance: " + account2.getBalance());
    }
}