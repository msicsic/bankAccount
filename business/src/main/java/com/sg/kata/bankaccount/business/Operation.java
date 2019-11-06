package com.sg.kata.bankaccount.business;

import java.time.LocalDateTime;

public abstract class Operation {
    private int amount;
    private LocalDateTime date;

    public Operation(int amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public int getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
