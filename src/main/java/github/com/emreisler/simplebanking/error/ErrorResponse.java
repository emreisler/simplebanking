package github.com.emreisler.simplebanking.error;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Long timestamp;
    private String message;

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }

    public ErrorResponse(String message) {
        this.timestamp = Instant.now().toEpochMilli();
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
