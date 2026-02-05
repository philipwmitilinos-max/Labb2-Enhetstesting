package se.iths.philip.bankkonto.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.philip.bankkonto.component.AccountComponent;
import se.iths.philip.bankkonto.exceptions.InsufficientFundsException;
import se.iths.philip.bankkonto.exceptions.InvalidAmountException;
import se.iths.philip.bankkonto.exceptions.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ATMServiceTest {

    @InjectMocks
    private ATMService atmService;

    @Mock
    private AccountComponent accountComponent;

    /**
     * Testar med Mockito.
     * 1. Saldo skapas.
     * -
     * 2. Insättning på saldo. Deposit
     * 3. Insättningen är större än 0. Deposit
     * 4. Insättningen är inte en tillåten summa (0 eller Negativ). Deposit
     * -
     * 5. Uttag ifrån saldo. Withdraw
     * 6. Uttaget är större än 0. Withdraw
     * 7. Uttaget är inte en tillåten summa (0 eller Negativ). Withdraw
     * 8. Uttaget inte får vara störe än vad fins på saldo. Withdraw
     */

    // --INSÄTTNING--
    @Test
    public void testDepositToSaldo() {
        //Testar att ATM gör insättning på saldo.
        atmService.deposit(1000);
        verify(accountComponent).deposit(1000);
    }

    @Test
    public void testInvalidDepositNotZeroToSaldo() {
        //Testar att ATM insättning inte är noll.
        assertThrows(InvalidAmountException.class,
                () -> atmService.deposit(0));
    }

    @Test
    public void testInvalidDepositNotNegativToSaldo() {
        //Testar att ATM insättning inte är negativ.
        assertThrows(InvalidAmountException.class,
                () -> atmService.deposit(-100));
    }

    // --UTTAG--
    @Test
    public void testWithdrawFormSaldo() {
        //Testar att ATM kan uttag ifrån saldo.
        when(accountComponent.getSaldo()).thenReturn(1000);
        atmService.withdraw(300);
        verify(accountComponent).withdraw(300);
    }

    @Test
    public void testInvalidWithdrawNotZeroFromSaldo() {
        //Testar att ATM uttag inte är noll.
        assertThrows(InvalidAmountException.class,
                () -> atmService.withdraw(0));
    }

    @Test
    public void testInvalidWithdrawNotNegativFromSaldo() {
        //Testar att ATM uttag inte är negativ.
        assertThrows(InvalidAmountException.class,
                () -> atmService.withdraw(-100));
    }

    @Test
    public void testMaxWithdrawExceeded() {
        //Testar att man inte kan göra ett för stort uttag.
        int tooMuch = 10000;
        assertThrows(MaxWithdrawalExceededException.class,
                () -> atmService.withdraw(tooMuch));
    }

    @Test
    public void testInsufficientFunds() {
        //Testar om det inte finns till räckligt på saldo.
        when(accountComponent.getSaldo()).thenReturn(200);
        assertThrows(InsufficientFundsException.class,
                () -> atmService.withdraw(500));
    }

    // --SALDO--
    @Test
    public void testGetSaldoReturnSaldoFromAccountComponent() {
        //Testar att hämta saldo.
        when(accountComponent.getSaldo()).thenReturn(1000);
        int saldo = atmService.getSaldo();
        verify(accountComponent).getSaldo();
        assert saldo == 1000;
    }
}
