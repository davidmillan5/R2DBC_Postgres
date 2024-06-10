package co.com.bancolombia.demo.controllers;

import co.com.bancolombia.demo.domain.entities.Transaction;
import co.com.bancolombia.demo.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public Mono<ResponseEntity<Transaction>> createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction)
                .map(savedTransaction -> ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transaction>> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Transaction>> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return transactionService.updateTransaction(transaction)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id)
                .then(Mono.just(ResponseEntity.noContent().<Object>build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

}