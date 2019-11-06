package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessValue;

@BusinessValue
public class Deposit extends Operation {

    public Deposit(int amount) {
        super(amount);
    }

}
