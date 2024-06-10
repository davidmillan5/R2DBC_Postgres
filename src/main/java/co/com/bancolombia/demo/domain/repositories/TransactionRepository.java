package co.com.bancolombia.demo.domain.repositories;

import co.com.bancolombia.demo.domain.entities.Transaction;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction,Long> {
}
