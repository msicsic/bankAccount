package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.exceptions.AccountAlreadyExistsException;
import com.sg.kata.bankaccount.business.exceptions.InsuffisantBalanceException;
import com.sg.kata.bankaccount.business.exceptions.UnknownAccountException;
import com.sg.kata.bankaccount.business.portIn.AccountAPI;
import com.sg.kata.bankaccount.business.portOut.AccountRepository;
import com.sg.kata.bankaccount.testsetup.AccountRepositoryTest;
import com.sg.kata.bankaccount.testsetup.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
class AccountAPITest {
    @Autowired
    private AccountAPI accountAPI;

    @Autowired
    private AccountRepository repo;

    @SuppressWarnings("unused")
    @BeforeEach
    private void cleanup() {
        ((AccountRepositoryTest)repo).removeAll();
    }

    @Test
    void should_be_possible_to_create_a_new_non_existing_account() {
        // GIVEN
        var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));

        // WHEN
        var accountID = accountAPI.createAccount(client);

        // THEN
        assertNotNull(accountID);
    }

    @Test
    void should_not_be_possible_to_create_an_already_existing_account() {
        assertThrows(AccountAlreadyExistsException.class, () -> {
            // GIVEN
            var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
            accountAPI.createAccount(client);

            // WHEN
            accountAPI.createAccount(client);

            // THEN Exception
        });
    }

    @Test
    void should_find_an_existing_account() {
        // GIVEN
        var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
        var accountID = accountAPI.createAccount(client);

        // WHEN
        var toTest = accountAPI.get(accountID);

        // THEN
        assertNotNull(toTest);
        assertEquals(accountID, toTest.getId());
    }

    @Test
    void should_not_find_a_non_existing_account() {
        assertThrows(UnknownAccountException.class, () -> {
            // GIVEN
            var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
            var account = new Account(client);

            // WHEN
            accountAPI.get(account.getId());

            // THEN Exception
        });
    }

    @Test
    void deposit_new_balance_should_be_correct() {

        // GIVEN
        var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
        var accountID = accountAPI.createAccount(client);

        // WHEN
        accountAPI.deposit(accountID, 100);

        // THEN
        var updatedAccount = accountAPI.get(accountID);
        assertEquals(100, updatedAccount.getBalance());
    }

    @Test
    void withdraw_with_enough_money_new_balance_should_be_correct() {

        // GIVEN
        var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
        var accountID = accountAPI.createAccount(client);
        accountAPI.deposit(accountID, 500);

        // WHEN
        accountAPI.withDraw(accountID, 100);

        // THEN
        var updatedAccount = accountAPI.get(accountID);
        assertEquals(400, updatedAccount.getBalance());
    }

    @Test
    void withdraw_with_not_enough_money_should_not_be_possible() {
        assertThrows(InsuffisantBalanceException.class, () -> {
            // GIVEN
            var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
            var accountID = accountAPI.createAccount(client);
            accountAPI.deposit(accountID, 500);

            // WHEN
            accountAPI.withDraw(accountID, 1000);

            // THEN Exception
        });
    }

    @Test
    void statement_history_should_be_correct() {
        // GIVEN
        var client = new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17));
        var accountID = accountAPI.createAccount(client);
        var operation1 = accountAPI.deposit(accountID, 100);
        var operation2 = accountAPI.withDraw(accountID, 30);
        var operation3 = accountAPI.withDraw(accountID, 20);

        // WHEN
        var res = accountAPI.getOperationHistory(accountID);

        // THEN
        assertEquals(3, res.size());

        assertEquals("Deposit", res.get(0).getName());
        assertEquals(operation1.getDate(), res.get(0).getDate());
        assertEquals(100, res.get(0).getAmount());
        assertEquals(100, res.get(0).getBalance());

        assertEquals("Withdraw", res.get(1).getName());
        assertEquals(operation2.getDate(), res.get(1).getDate());
        assertEquals(-30, res.get(1).getAmount());
        assertEquals(70, res.get(1).getBalance());

        assertEquals("Withdraw", res.get(2).getName());
        assertEquals(operation3.getDate(), res.get(2).getDate());
        assertEquals(-20, res.get(2).getAmount());
        assertEquals(50, res.get(2).getBalance());
    }
}