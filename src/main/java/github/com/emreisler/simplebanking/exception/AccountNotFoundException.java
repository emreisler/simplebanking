package github.com.emreisler.simplebanking.exception;

import github.com.emreisler.simplebanking.model.SimpleBankingException;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends SimpleBankingException {
    public AccountNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
