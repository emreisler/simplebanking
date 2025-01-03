package github.com.emreisler.simplebanking.controller;

import github.com.emreisler.simplebanking.dto.AmountDto;
import github.com.emreisler.simplebanking.exception.BadAmountException;
import github.com.emreisler.simplebanking.model.Account;
import github.com.emreisler.simplebanking.model.DepositTransaction;
import github.com.emreisler.simplebanking.model.WithdrawalTransaction;
import github.com.emreisler.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody AmountDto amountDto) {
        if (amountDto.getAmount() <= 0) {
            throw new BadAmountException("Amount must be greater than zero");
        }
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(accountService.post(account.getAccountNumber(), new DepositTransaction(amountDto.getAmount())));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody AmountDto amountDto) {
        if (amountDto.getAmount() <= 0) {
            throw new BadAmountException("Amount must be greater than zero");
        }
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(accountService.post(account.getAccountNumber(), new WithdrawalTransaction(amountDto.getAmount())));
    }
}