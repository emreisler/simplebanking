package github.com.emreisler.simplebanking.exception;


import github.com.emreisler.simplebanking.model.SimpleBankingException;
import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends SimpleBankingException {
    public InsufficientBalanceException() {
        super(HttpStatus.BAD_REQUEST, "insufficient balance");
    }
}
