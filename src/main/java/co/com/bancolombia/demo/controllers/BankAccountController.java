package co.com.bancolombia.demo.controllers;

import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.services.BankAccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;


    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public Mono<ResponseEntity<BankAccount>> createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount)
                .map(savedAccount -> ResponseEntity.status(HttpStatus.CREATED).body(savedAccount));
    }

    @GetMapping
    public Flux<BankAccount> getAllBankAccounts(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountService.getAllBankAccounts(pageable);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BankAccount>> getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BankAccount>> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        bankAccount.setId(id);
        return bankAccountService.updateBankAccount(bankAccount)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteBankAccount(@PathVariable Long id) {
        return bankAccountService.deleteBankAccount(id)
                .then(Mono.just(ResponseEntity.noContent().<Object>build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
}
