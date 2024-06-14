package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.domain.entities.Transaction;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> createTransaction(Transaction transaction);
    Flux<Transaction> getAllTransactions(Pageable pageable);
    Mono<Transaction> getTransactionById(Long id);
    Mono<Transaction> updateTransaction(Transaction transaction);
    Mono<Void> deleteTransaction(Long id);
}
