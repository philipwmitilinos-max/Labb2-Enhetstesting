package se.iths.philip.bankkonto.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaldoPlaywrightTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeEach
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    void closeContext() {
        context.close();
    }

    @Test
    void testSaloPageLoadAndShowZero() {
        //Går till applikationen.
        page.navigate("http://localhost:8080/");

        //Kontrollera att saldofältet finns.
        Locator saldoElement = page.locator("#saldo");

        //Kontrolleraa att saldot visas korrekt.
        String saldoText = saldoElement.textContent();
        assertEquals("0", saldoText);
    }
}
