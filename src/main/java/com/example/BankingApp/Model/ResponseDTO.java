package com.example.BankingApp.Model;

import org.springframework.http.HttpStatus;
import com.example.BankingApp.Model.BankAccount;

import java.util.List;

public class ResponseDTO {
    private List<BankAccount> body;
    private HttpStatus httpStatus;
    private String message;

    public List<BankAccount> getBody() {
        return body;
    }

    public void setBody(List<BankAccount> body) {
        this.body = body;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
