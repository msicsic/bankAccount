package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessValue;

import java.time.LocalDateTime;

@BusinessValue
public class Statement {
    private Operation operation;
    private int balance;

    public Statement(Operation operation, int balance) {
        this.operation = operation;
        this.balance = balance;
    }

    public String getName() {
        return this.operation.getName();
    }

    public LocalDateTime getDate() {
        return this.operation.getDate();
    }

    public int getAmount() {
        return this.operation.getAmount();
    }

    public int getBalance() {
        return balance;
    }
}
