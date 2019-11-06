package bdd;

import com.sg.kata.bankaccount.business.AccountID;
import com.sg.kata.bankaccount.business.portIn.AccountAPI;
import com.sg.kata.bankaccount.business.portOut.AccountRepository;
import com.sg.kata.bankaccount.testsetup.AccountRepositoryTest;
import com.sg.kata.bankaccount.testsetup.TestConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/bdd/features",
        glue = {"bdd.steps"},
        plugin = {"pretty", "html:target/cucumber"}
)
public class CucumberTest {

    @Autowired
    protected AccountAPI api;

    @Autowired
    protected AccountRepository repo;

    protected AccountID accountID;

    protected void cleanup() {
        ((AccountRepositoryTest) repo).removeAll();
    }
}
