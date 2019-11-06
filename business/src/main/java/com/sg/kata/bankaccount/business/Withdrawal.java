package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessValue;

@BusinessValue
public class Withdrawal extends Operation {

    public Withdrawal(int amount) {
        super(-amount);
    }
}
