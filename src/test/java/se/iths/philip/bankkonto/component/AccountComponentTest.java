package se.iths.philip.bankkonto.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AccountComponentTest {
    private AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();
    }

    /**
     * Testning:
     * Saldo kan ta in insättning 0 + x
     * Saldo kan ge ut uttag 0 - y
     * Kontroll av saldo är 0
     * Att sätta in och ta ut ifrån saldo i samma metod 0 + x - y = xy
     */


    @Test
    public void testAccountIsZero() {
        //Testar att saldot är noll
        assertEquals("Saldo är 0 när kontot skapas", 0,
                accountComponent.getSaldo());
    }

    @Test
    public void testAddToAccount() {
        //Testar att det kommer in på saldot
        accountComponent.deposit(1000); //act
        assertEquals("Saldo ska öka efter insättning", 1000,
                accountComponent.getSaldo()); //assert
    }

    @Test
    public void testTakeFromAccount() {
        //Testat att ta ut ifrån saldot
        accountComponent.deposit(1000);
        accountComponent.withdraw(450); //act
        assertEquals("Saldo ska minska efter uttag", 550,
                accountComponent.getSaldo()); //assert
    }

    @Test
    public void testTakeNotGreaterFromAccount() {
        //Testar att det inte går att ta ut när saldo är 0
    }

    @Test
    public void testPutInAndOutFromAccount() {
        //Testar att sätta in och att ta ut ifrån saldot. (Jag behöver inte göra det här testet)
        accountComponent.deposit(1000);
        accountComponent.withdraw(400);
        accountComponent.withdraw(250);
        assertEquals("Saldo ska minskat två gånger", 350,
                accountComponent.getSaldo());
    }
}
