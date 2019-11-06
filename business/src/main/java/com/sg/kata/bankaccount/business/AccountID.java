package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessValue;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * for this Kata, we suppose that each person can only own 1 account,
 * so the AccountID is based on the person name
 */
@BusinessValue
public class AccountID {
    private String value;

    public AccountID(String value) {
        if (value == null) throw new IllegalArgumentException();
        this.value = value;
    }

    public AccountID(Client client) {
        this(client.getFirstName()+"_"+client.getLastName()+"_"+client.getDob().format(DateTimeFormatter.ISO_DATE));
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountID accountID = (AccountID) o;
        return value.equals(accountID.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
