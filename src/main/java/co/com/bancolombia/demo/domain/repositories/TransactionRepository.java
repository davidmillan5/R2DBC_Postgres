package co.com.bancolombia.demo.domain.repositories;

import co.com.bancolombia.demo.domain.entities.Transaction;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TransactionRepository extends R2dbcRepository<Transaction,Long> {
}
