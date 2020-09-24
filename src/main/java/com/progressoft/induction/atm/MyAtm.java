package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MyAtm implements ATM {
    private int fives = 100, tens = 100,
                twenties = 20, fifties = 10;

    private final MyBankingSystem bank = new MyBankingSystem();

    public BigDecimal calculateAtmSupply() {
        int supply = fives*5 + tens*10
                    + twenties*20 + fifties*50;
        return new BigDecimal(supply);
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {

        int balance_comparison = amount.compareTo(bank.getAccountBalance(accountNumber));
        if (balance_comparison == 1) throw new InsufficientFundsException();

        int supply_comparison = amount.compareTo(calculateAtmSupply());
        if (supply_comparison == 1) throw new NotEnoughMoneyInATMException();

        List<Banknote> banknotes = new ArrayList<>();
        BigDecimal amount_clone = new BigDecimal(amount.toString());

        while (amount_clone.compareTo(BigDecimal.ZERO) == 1) {
            if ((amount_clone.compareTo(Banknote.FIFTY_JOD.getValue()) >= 0)
                    && (fifties != 0)
                    && (amount_clone.compareTo(new BigDecimal("50")) == 1)) {
                amount_clone = amount_clone.subtract(Banknote.FIFTY_JOD.getValue());
                banknotes.add(Banknote.FIFTY_JOD);
                fifties--;
            }
            else if ((amount_clone.compareTo(Banknote.TWENTY_JOD.getValue()) >= 0)
                    && (twenties != 0)) {
                amount_clone = amount_clone.subtract(Banknote.TWENTY_JOD.getValue());
                banknotes.add(Banknote.TWENTY_JOD);
                twenties--;
            }
            else if ((amount_clone.compareTo(Banknote.TEN_JOD.getValue()) >= 0)
                    && (tens != 0)) {
                amount_clone = amount_clone.subtract(Banknote.TEN_JOD.getValue());
                banknotes.add(Banknote.TEN_JOD);
                tens--;
            }
            else if ((amount_clone.compareTo(Banknote.FIVE_JOD.getValue()) >= 0)
                    && (fives != 0)) {
                amount_clone = amount_clone.subtract(Banknote.FIVE_JOD.getValue());
                banknotes.add(Banknote.FIVE_JOD);
                fives--;
            }
        }
        bank.debitAccount(accountNumber, amount);
        return banknotes;
    }
}
