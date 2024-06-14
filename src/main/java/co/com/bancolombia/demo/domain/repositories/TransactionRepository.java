package co.com.bancolombia.demo.domain.repositories;

import co.com.bancolombia.demo.domain.entities.Transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction,Long> {
    Flux<Transaction> findByBankAccountId(Long bankAccountId);
    Flux<Transaction> findAll(Pageable pageable);
}
