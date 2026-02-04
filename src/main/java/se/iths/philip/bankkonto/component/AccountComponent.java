package se.iths.philip.bankkonto.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {

    private int saldo = 0;

    //Deposit för att sätta in på saldo
    public void deposit(int amount) {
        saldo += amount;
    }

    //Withdraw för att ta ut ifrån saldo
    public void withdraw(int amount) {
        saldo -= amount;
    }

    //För att kunna hämta saldo
    public int getSaldo() {
        return saldo;
    }
}
