package com.sg.kata.bankaccount.infra.adapterOut;

import com.sg.kata.bankaccount.business.Account;
import com.sg.kata.bankaccount.business.AccountID;
import com.sg.kata.bankaccount.business.exceptions.AccountAlreadyExistsException;
import com.sg.kata.bankaccount.business.exceptions.UnknownAccountException;
import com.sg.kata.bankaccount.business.portOut.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class AccountRepositoryMemory implements AccountRepository {
    private Map<AccountID, Account> cache = new HashMap<>();

    @Override
    public void registerNew(Account account) {
        if (cache.containsKey(account.getId())) throw new AccountAlreadyExistsException();
        cache.put(account.getId(), account);
    }

    @Override
    public <R> R update(AccountID id, Function<Account, R> function) {
        return function.apply(get(id));
    }

    @Override
    public Account get(AccountID id) {
        if (! cache.containsKey(id)) throw new UnknownAccountException();
        return cache.get(id);
    }
}
