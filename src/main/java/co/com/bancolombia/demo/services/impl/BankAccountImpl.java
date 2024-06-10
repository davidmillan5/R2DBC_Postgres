package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import co.com.bancolombia.demo.domain.repositories.UserRepository;
import co.com.bancolombia.demo.services.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountImpl implements BankAccountService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public Mono<BankAccount> createBankAccount(BankAccount account, Long userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new RuntimeException("No user with the given Id")))
                .flatMap(user -> {
                    account.setUser(user);
                    return bankAccountRepository.save(account);
                });
    }

    @Override
    public Flux<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Bank account not found")));
    }

    @Override
    @Transactional
    public Mono<BankAccount> updateBankAccount(BankAccount account) {
        return bankAccountRepository.findById(account.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Bank account not found")))
                .flatMap(existingAccount -> {
                    existingAccount.setAccountType(account.getAccountType());
                    existingAccount.setBalance(account.getBalance());
                    return bankAccountRepository.save(existingAccount);
                });
    }

    @Override
    @Transactional
    public Mono<Void> deleteBankAccount(Long id) {
        return bankAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Bank account not found")))
                .flatMap(bankAccountRepository::delete);
    }
}
