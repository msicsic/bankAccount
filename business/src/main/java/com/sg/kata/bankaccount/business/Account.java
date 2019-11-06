package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessAggregate;
import com.sg.kata.bankaccount.business.dddframework.Entity;
import com.sg.kata.bankaccount.business.exceptions.AmountMustBePositiveException;
import com.sg.kata.bankaccount.business.exceptions.InsuffisantBalanceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Account aggregate</b>
 * To simplify, the balance is an integer
 */
@BusinessAggregate
public class Account extends Entity<AccountID> {
    private List<Operation> operations = new ArrayList<>();
    private int currentBalance;

    public Account(Client client) {
        super(new AccountID(client));
    }

    public AccountID getId() {
        return id;
    }

    public int getBalance() {
        return currentBalance;
    }

    public synchronized Deposit deposit(int amount) {
        // amount must be > 0
        if (amount <= 0) throw new AmountMustBePositiveException();

        // deposit is always possible
        var deposit = new Deposit(amount);
        updateBalance(deposit);
        return deposit;
    }

    public synchronized Withdrawal withDraw(int amount) {
        // amount must be > 0
        if (amount <= 0) throw new AmountMustBePositiveException();

        var withDraw = new Withdrawal(amount);

        // check balance
        if (currentBalance + withDraw.getAmount() < 0) throw new InsuffisantBalanceException();

        // perform deposit
        updateBalance(withDraw);

        return withDraw;
    }

    public synchronized List<Statement> getOperationHistory() {
        List<Statement> result = new ArrayList<>();
        int balance = 0;
        for (Operation operation : operations) {
            balance += operation.getAmount();
            result.add(new Statement(operation, balance));
        }
        return result;
    }

    private void updateBalance(Operation operation) {
        this.operations.add(operation);
        this.currentBalance += operation.getAmount();
    }

}
