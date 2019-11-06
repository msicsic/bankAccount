package com.sg.kata.bankaccount.business.portOut;

import com.sg.kata.bankaccount.business.Account;
import com.sg.kata.bankaccount.business.AccountID;
import com.sg.kata.bankaccount.business.exceptions.AccountAlreadyExistsException;
import com.sg.kata.bankaccount.business.exceptions.UnknownAccountException;

import java.util.function.Function;

public interface AccountRepository {

    /**
     * Register a non existing account
     * @param account the new account to store in this repo
     * @throws AccountAlreadyExistsException if this account is already present in the repo
     */
    void registerNew(Account account);

    /**
     * Performs the update operation on the aggregate corresponding to the ID
     * @param id ID of an account
     * @param update a lambda that receives the loaded account and perform an operation on it in a transaction
     * @throws UnknownAccountException if account does not exist
     */
    <R> R update(AccountID id, Function<Account, R> update);

    /**
     * Get an Account by its ID
     * @param id ID of the account to retrieve
     * @return the loaded Account
     * @throws UnknownAccountException if this account does not exist
     */
    Account get(AccountID id);

}
