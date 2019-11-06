package com.sg.kata.bankaccount.infra.adapterIn;

import com.sg.kata.bankaccount.business.*;
import com.sg.kata.bankaccount.business.portIn.AccountAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping(path = "/api/v1/account")
public class AccountRestAdapter {
    private AccountAPI api;

    @Autowired
    public AccountRestAdapter(AccountAPI api) {
        this.api = api;
    }

    @GetMapping(path = "/{accountId:.*}")
    public Account get(@PathVariable String accountId) {
        return api.get(new AccountID(accountId));
    }

    @PostMapping
    public AccountID createAccount(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate dob) {
        return api.createAccount(new Client(firstname, lastname, dob));
    }

    @PutMapping(path = "/{accountId:.*}/deposit")
    public Operation deposit(
            @PathVariable String accountId,
            @RequestParam int amount) {
        return api.deposit(new AccountID(accountId), amount);
    }

    @PutMapping(path = "/{accountId:.*}/withdraw")
    public Operation withDraw(
            @PathVariable String accountId,
            @RequestParam int amount) {
        return api.withDraw(new AccountID(accountId), amount);
    }

    @GetMapping(path = "/{accountId:.*}/history")
    public List<Statement> getOperationHistory(
            @PathVariable String accountId) {
        return api.getOperationHistory(new AccountID(accountId));
    }
}
