package com.sg.kata.bankaccount.business.exceptions;

import com.sg.kata.bankaccount.business.dddframework.BusinessException;

/**
 * When trying to create an already existing account
 */
public class AmountMustBePositiveException extends BusinessException {
    public AmountMustBePositiveException() {
        super("Amount must always be positive");
    }
}
