package com.sg.kata.bankaccount.business.exceptions;

import com.sg.kata.bankaccount.business.dddframework.BusinessException;

/**
 * When trying to create an already existing account
 */
public class AccountAlreadyExistsException extends BusinessException {
    public AccountAlreadyExistsException() {
        super("this account already exists");
    }
}
