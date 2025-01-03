package github.com.emreisler.simplebanking.advice;


import github.com.emreisler.simplebanking.error.ErrorResponse;
import github.com.emreisler.simplebanking.exception.InsufficientBalanceException;
import github.com.emreisler.simplebanking.model.SimpleBankingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.OptimisticLockException;

@ControllerAdvice
public class SimpleBankingControllerAdvice {

    @ExceptionHandler(SimpleBankingException.class)
    public ResponseEntity<ErrorResponse> handleSimpleBankingException(SimpleBankingException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<String> handleOptimisticLockException(OptimisticLockException ex) {
        return ResponseEntity.status(409).body("The resource was updated by another transaction. Please retry.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(InsufficientBalanceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(ex.getMessage()));
    }

}
