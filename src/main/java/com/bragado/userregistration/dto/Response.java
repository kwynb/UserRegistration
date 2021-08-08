package com.bragado.userregistration.dto;

public class Response {
    private String error;

    public Response(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Response{" +
                "error='" + error + '\'' +
                '}';
    }
}
