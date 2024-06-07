package co.com.bancolombia.demo.repositories;

import co.com.bancolombia.demo.models.Transaction;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction,Long> {
}
