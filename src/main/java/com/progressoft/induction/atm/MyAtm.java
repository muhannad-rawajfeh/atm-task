package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** TODO:
 * duplication
 */

public class MyAtm implements ATM {

    private int fives = 100, tens = 100,
                twenties = 20, fifties = 10;

    private final MyBankingSystem bank = new MyBankingSystem();
    private final List<Banknote> banknotes = new ArrayList<>();

    private BigDecimal calculateAtmSupply() {
        int supply = fives*5 + tens*10
                    + twenties*20 + fifties*50;
        return new BigDecimal(supply);
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {

        int balanceComparison = amount.compareTo(bank.getAccountBalance(accountNumber));
        if (balanceComparison > 0) throw new InsufficientFundsException();

        int supplyComparison = amount.compareTo(calculateAtmSupply());
        if (supplyComparison > 0) throw new NotEnoughMoneyInATMException();

        BigDecimal amountClone = new BigDecimal(amount.toString());

        while (amountClone.compareTo(BigDecimal.ZERO) > 0) {

            if ((amountClone.compareTo(Banknote.FIFTY_JOD.getValue()) > 0)
                    && (fifties != 0)) {
                amountClone = amountClone.subtract(Banknote.FIFTY_JOD.getValue());
                banknotes.add(Banknote.FIFTY_JOD);
                fifties--;
            }
            else if ((amountClone.compareTo(Banknote.TWENTY_JOD.getValue()) >= 0)
                    && (twenties != 0)) {
                amountClone = amountClone.subtract(Banknote.TWENTY_JOD.getValue());
                banknotes.add(Banknote.TWENTY_JOD);
                twenties--;
            }
            else if ((amountClone.compareTo(Banknote.TEN_JOD.getValue()) >= 0)
                    && (tens != 0)) {
                amountClone = amountClone.subtract(Banknote.TEN_JOD.getValue());
                banknotes.add(Banknote.TEN_JOD);
                tens--;
            }
            else if ((amountClone.compareTo(Banknote.FIVE_JOD.getValue()) >= 0)
                    && (fives != 0)) {
                amountClone = amountClone.subtract(Banknote.FIVE_JOD.getValue());
                banknotes.add(Banknote.FIVE_JOD);
                fives--;
            }
        }
        bank.debitAccount(accountNumber, amount);
        return banknotes;
    }
}
