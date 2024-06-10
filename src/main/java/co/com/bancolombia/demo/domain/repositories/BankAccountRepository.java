package co.com.bancolombia.demo.domain.repositories;


import co.com.bancolombia.demo.domain.entities.BankAccount;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface BankAccountRepository extends ReactiveCrudRepository<BankAccount,Long> {
    @Query("SELECT * FROM bank_accounts WHERE user_id = :userId")
    Flux<BankAccount> findByUserId(Long userId);
}
