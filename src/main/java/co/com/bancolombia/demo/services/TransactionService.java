package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.domain.entities.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> createTransaction(Transaction transaction, Long accountId);
    Flux<Transaction> getAllTransactions();
    Mono<Transaction> getTransactionById(Long id);
    Mono<Transaction> updateTransaction(Transaction transaction);
    Mono<Void> deleteTransaction(Long id);
}
