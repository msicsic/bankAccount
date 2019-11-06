package com.sg.kata.bankaccount.business.exceptions;

import com.sg.kata.bankaccount.business.dddframework.BusinessException;

/**
 * When trying to create an already existing account
 */
public class UnknownAccountException extends BusinessException {
    public UnknownAccountException() {
        super("this account does not exist");
    }
}
