package github.com.emreisler.simplebanking.model;

import org.springframework.http.HttpStatus;

public class SimpleBankingException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public SimpleBankingException(HttpStatus status,String message){
        super(message);
        this.status = status;
        this.message = message;
    }

    public SimpleBankingException(String message){
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
