package co.com.bancolombia.demo.services;


import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.domain.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    Mono<BankAccount> createBankAccount(BankAccount account, Long userId);
    Flux<BankAccount> getAllBankAccounts();
    Mono<BankAccount> getBankAccountById(Long id);
    Mono<BankAccount> updateBankAccount(BankAccount account);
    Mono<Void> deleteBankAccount(Long id);
}



