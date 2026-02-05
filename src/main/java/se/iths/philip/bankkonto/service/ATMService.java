package se.iths.philip.bankkonto.service;

import org.springframework.stereotype.Service;
import se.iths.philip.bankkonto.component.AccountComponent;
import se.iths.philip.bankkonto.exceptions.InsufficientFundsException;
import se.iths.philip.bankkonto.exceptions.InvalidAmountException;
import se.iths.philip.bankkonto.exceptions.MaxWithdrawalExceededException;

@Service
public class ATMService {
    private final AccountComponent accountComponent;

    //Sätter max uttaget.
    private static final int MAX_WITHDRAWAL = 5000;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void deposit(int amount) {
        //Ser till att insättnings beloppet inte är 0 eller mindre.
        if (amount <= 0) {
            throw new InvalidAmountException("Belopp måste vara större än 0");
        }
        accountComponent.deposit(amount);
    }

    public void withdraw(int amount) {
        //Ser till att uttags beloppet inte är 0 eller mindre.
        if (amount <= 0) {
            throw new InvalidAmountException("Belopp måste vara större än 0");
        }
        //Används när uttaget är större än maxbelopp per uttag.
        if (amount > MAX_WITHDRAWAL) {
            throw new MaxWithdrawalExceededException("Överskrider maxbeloppet");
        }
        //Används när uttaget är större än det aktuella saldo.
        if (amount > accountComponent.getSaldo()) {
            throw new InsufficientFundsException("Otillräckligt saldo");
        }
        accountComponent.withdraw(amount);
    }

    //Ger tillgång till saldo.
    public int getSaldo() {
        return accountComponent.getSaldo();
    }
}
