package org.example.exception.handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Ofir Mamo
 * created at 29/11/2020
 */
@Data
public class RestRequestError {

    private HttpStatus returnStatus;
    private long timestamp;
    private String message;

    private RestRequestError() {
        this.timestamp = System.currentTimeMillis();
    }

    RestRequestError(HttpStatus returnStatus, String message) {
        this();
        this.returnStatus = returnStatus;
        this.message = message;
    }
}
