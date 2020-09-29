package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.HashMap;

public class MyBankingSystem implements BankingSystem {

    HashMap<String, BigDecimal> bankAccounts = new HashMap<>();

    MyBankingSystem() {
        bankAccounts.put("123456789", new BigDecimal(1000.0));
        bankAccounts.put("111111111", new BigDecimal(1000.0));
        bankAccounts.put("222222222", new BigDecimal(1000.0));
        bankAccounts.put("333333333", new BigDecimal(1000.0));
        bankAccounts.put("444444444", new BigDecimal(1000.0));
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        if (bankAccounts.containsKey(accountNumber)) {
            return bankAccounts.get(accountNumber);
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        if (bankAccounts.containsKey(accountNumber)) {
            bankAccounts.replace(accountNumber, bankAccounts.get(accountNumber).subtract(amount));
        }
        else {
            throw new AccountNotFoundException();
        }
    }
}
