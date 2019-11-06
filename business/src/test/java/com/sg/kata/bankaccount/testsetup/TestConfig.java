package com.sg.kata.bankaccount.testsetup;

import com.sg.kata.bankaccount.business.portIn.AccountAPI;
import com.sg.kata.bankaccount.business.portOut.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public AccountAPI accountAPI() {
        return new AccountAPI(accountRepo());
    }

    @Bean
    public AccountRepository accountRepo() {
        return new AccountRepositoryTest();
    }
}
