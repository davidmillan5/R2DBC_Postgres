package co.com.bancolombia.demo.repositories;


import co.com.bancolombia.demo.models.BankAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BankAccountRepository extends ReactiveCrudRepository<BankAccount,Long> {
}
