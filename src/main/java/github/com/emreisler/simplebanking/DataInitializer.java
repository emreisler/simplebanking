package github.com.emreisler.simplebanking;

import github.com.emreisler.simplebanking.converters.AccountConverter;
import github.com.emreisler.simplebanking.model.Account;
import github.com.emreisler.simplebanking.model.DepositTransaction;
import github.com.emreisler.simplebanking.model.WithdrawalTransaction;
import github.com.emreisler.simplebanking.persistence.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "data", name = "initialize", havingValue = "true", matchIfMissing = false)
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public DataInitializer( AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        // Create accounts
        Account account1 = new Account("Kerem Karaca", "669-7788");
        Account account2 = new Account("John Doe", "123-4567");

        // Add initial transactions
        account1.post(new DepositTransaction(1000));
        account1.post(new WithdrawalTransaction(200));

        account2.post(new DepositTransaction(500));
        account2.post(new WithdrawalTransaction(300));

        // Save accounts to the repository
        accountRepository.save(AccountConverter.toEntity(account1));
        accountRepository.save(AccountConverter.toEntity(account2));

        System.out.println("Initialized accounts:");
        System.out.println("Account 1: " + account1.getAccountNumber() + ", Balance: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getAccountNumber() + ", Balance: " + account2.getBalance());
    }
}