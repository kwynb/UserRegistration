package com.bragado.userregistration.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private HttpStatus      status;
    private String          message;
    private List<String>    errors;

    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status     = status;
        this.message    = message;
        errors          = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}