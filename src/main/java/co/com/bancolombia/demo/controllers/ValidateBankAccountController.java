package co.com.bancolombia.demo.controllers;

import co.com.bancolombia.demo.services.ValidateBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/validate")
public class ValidateBankAccountController {

    private final ValidateBankAccountService validateBankAccount;

    public ValidateBankAccountController(ValidateBankAccountService validateBankAccount) {
        this.validateBankAccount = validateBankAccount;
    }

    @GetMapping("/bankaccount/{bankAccountId}")
    public Mono<String> validateBankAccount(@PathVariable Long bankAccountId){
        return validateBankAccount.validateBankAccount(bankAccountId);
    }
}
