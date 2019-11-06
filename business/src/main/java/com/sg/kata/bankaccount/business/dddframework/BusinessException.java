package com.sg.kata.bankaccount.business.dddframework;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
