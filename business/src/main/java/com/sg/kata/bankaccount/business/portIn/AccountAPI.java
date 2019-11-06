package com.sg.kata.bankaccount.business.portIn;

import com.sg.kata.bankaccount.business.*;
import com.sg.kata.bankaccount.business.portOut.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Main Account API</br>
 * Used by adapters to perform business operations
 */
@Component
public class AccountAPI {
    private AccountRepository accountRepo;

    @Autowired
    public AccountAPI(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account get(AccountID id) {
        return this.accountRepo.get(id);
    }

    public AccountID createAccount(Client client) {
        var account = new Account(client);
        this.accountRepo.registerNew(account);
        return account.getId();
    }

    public Operation deposit(AccountID accountID, int amount) {
        return this.accountRepo.update(accountID, (Account account) -> account.deposit(amount));
    }

    public Operation withDraw(AccountID accountID, int amount) {
        return this.accountRepo.update(accountID, (Account account) -> account.withDraw(amount));
    }

    public List<Statement> getOperationHistory(AccountID accountID) {
        return get(accountID).getOperationHistory();
    }

    public int getBalance(AccountID accountID) {
        return get(accountID).getBalance();
    }
}
