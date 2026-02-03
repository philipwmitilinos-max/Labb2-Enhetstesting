package se.iths.philip.bankkonto.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testAddToAccount() {
        //Testar att det kommer in på saldot
    }

    @Test
    public void testTakeFromAccount() {
        //Testat att ta ut ifrån saldot
    }

    @Test
    public void testTakeNotGreaterFromAccount() {
        //Testar att det inte går att ta ut när saldo är 0
    }

    @Test
    public void testPutInAndOutFromAccount() {
        //Testar att sätta in och att ta ut ifrån saldot
    }
}
