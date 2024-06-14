package co.com.bancolombia.demo.services;


import co.com.bancolombia.demo.domain.entities.BankAccount;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    Mono<BankAccount> createBankAccount(BankAccount account);
    Flux<BankAccount> getAllBankAccounts(Pageable pageable);
    Mono<BankAccount> getBankAccountById(Long id);
    Mono<BankAccount> updateBankAccount(BankAccount account);
    Mono<Void> deleteBankAccount(Long id);
}



