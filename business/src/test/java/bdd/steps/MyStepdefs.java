package bdd.steps;

import bdd.CucumberTest;
import com.sg.kata.bankaccount.business.Client;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("CodeBlock2Expr")
public class MyStepdefs extends CucumberTest implements En {

    public MyStepdefs() {

        Given("a new account", () -> {
            cleanup();
            accountID = api.createAccount(new Client("Mael", "Sicsic", LocalDate.of(1976, 5, 17)));
        });

        When("^a deposit of (\\d+) is performed$", (Integer amount) -> {
            api.deposit(accountID, amount);
        });

        When("a withdraw of {int} is performed", (Integer amount) -> {
            // Write code here that turns the phrase above into concrete actions
            api.withDraw(accountID, amount);
        });

        When("the following operations are done", (DataTable dataTable) -> {
            var res = dataTable.asList(COperation.class).stream().map(o -> (COperation) o).collect(Collectors.toList());
            res.forEach(cOperation -> {
                switch (cOperation.operation) {
                    case "Deposit" -> api.deposit(accountID, cOperation.amount);
                    case "Withdraw" -> api.withDraw(accountID, cOperation.amount);
                }
            });
        });

        Then("the balance should be {int}", (Integer balance) -> {
            assertEquals(balance, api.getBalance(accountID));
        });

        Then("the statement history should be", (DataTable dataTable) -> {
            var res = dataTable.asList(CStatement.class).stream().map(o -> (CStatement) o).collect(Collectors.toList());
            var expectedList = api.getOperationHistory(accountID);
            for (int i = 0; i < res.size(); i++) {
                var statement = res.get(i);
                var expected = expectedList.get(i);
                assertEquals(expected.getAmount(), statement.amount);
                assertEquals(expected.getBalance(), statement.balance);
                assertEquals(expected.getName(), statement.operation);
            }
        });
    }

    public static class CStatement {
        String operation;
        int amount;
        int balance;
    }

    public static class COperation {
        String operation;
        int amount;
    }
}
