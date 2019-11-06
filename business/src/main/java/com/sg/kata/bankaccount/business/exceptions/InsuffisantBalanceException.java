package com.sg.kata.bankaccount.business.exceptions;

import com.sg.kata.bankaccount.business.dddframework.BusinessException;

/**
 * When trying to create an already existing account
 */
public class InsuffisantBalanceException extends BusinessException {
    public InsuffisantBalanceException() {
        super("You don't have enough money");
    }
}
