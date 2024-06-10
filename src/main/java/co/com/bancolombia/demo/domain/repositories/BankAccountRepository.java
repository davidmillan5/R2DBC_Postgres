package co.com.bancolombia.demo.domain.repositories;


import co.com.bancolombia.demo.domain.entities.BankAccount;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface BankAccountRepository extends R2dbcRepository<BankAccount,Long> {
}
