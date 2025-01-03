package github.com.emreisler.simplebanking.exception;

import github.com.emreisler.simplebanking.model.SimpleBankingException;
import org.springframework.http.HttpStatus;

public class BadAmountException extends SimpleBankingException {
    public BadAmountException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
