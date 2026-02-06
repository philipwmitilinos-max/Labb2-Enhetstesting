package se.iths.philip.bankkonto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.philip.bankkonto.service.ATMService;

@Controller
public class AccountController {

    private final ATMService atmService;

    public AccountController(ATMService atmService) {
        this.atmService = atmService;
    }

    //Använder ATMService för att hämta saldo och ger den till en html-fil.
    @GetMapping("/")
    public String showSaldo(Model model) {
        model.addAttribute("saldo", atmService.getSaldo());
        return "saldo";
    }
}
