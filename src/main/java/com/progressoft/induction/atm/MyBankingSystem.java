package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;

public class MyBankingSystem implements BankingSystem {
    private final int NUMBER_OF_USERS = 5;
    private final String[] ACCOUNT_NUMBERS = new String[NUMBER_OF_USERS];
    private final BigDecimal[] BALANCES = new BigDecimal[NUMBER_OF_USERS];

    MyBankingSystem() {
        ACCOUNT_NUMBERS[0] = ("123456789");
        ACCOUNT_NUMBERS[1] = ("111111111");
        ACCOUNT_NUMBERS[2] = ("222222222");
        ACCOUNT_NUMBERS[3] = ("333333333");
        ACCOUNT_NUMBERS[4] = ("444444444");

        BALANCES[0] = (new BigDecimal(1000.0));
        BALANCES[1] = (new BigDecimal(1000.0));
        BALANCES[2] = (new BigDecimal(1000.0));
        BALANCES[3] = (new BigDecimal(1000.0));
        BALANCES[4] = (new BigDecimal(1000.0));
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {

        for(int i = 0; i < NUMBER_OF_USERS; i++) {
            if (ACCOUNT_NUMBERS[i] == accountNumber) {
                return BALANCES[i];
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            if (ACCOUNT_NUMBERS[i] == accountNumber) {
                BALANCES[i] = BALANCES[i].subtract(amount);
            }
        }
    }
}
