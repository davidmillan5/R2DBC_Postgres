package co.com.bancolombia.demo.domain.repositories;


import co.com.bancolombia.demo.domain.entities.BankAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BankAccountRepository extends ReactiveCrudRepository<BankAccount,Long> {
}
