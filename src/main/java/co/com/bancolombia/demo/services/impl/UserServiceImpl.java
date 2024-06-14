package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.domain.entities.User;
import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import co.com.bancolombia.demo.domain.repositories.TransactionRepository;
import co.com.bancolombia.demo.domain.repositories.UserRepository;
import co.com.bancolombia.demo.exceptions.UserNotFoundException;
import co.com.bancolombia.demo.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public UserServiceImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository
                .save(user);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository
                .findAll()
                .flatMap(this::populateUserAccounts); // Populate bank accounts for each user
    }


    @Override
    public Mono<User> getUserById(Long id) {
        return userRepository
                .findById(id)
                .flatMap(this::populateUserAccounts)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found")));
    }

    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .flatMap(existingUser  ->{
                    existingUser .setName(user.getName());
                    return this.userRepository.save(existingUser );
                })
                .switchIfEmpty(this.userRepository.save(user.setAsNew()));
    }

    @Override
    @Transactional
    public Mono<Void> deleteUser(Long id) {
        return userRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found")))
                .flatMap(userRepository::delete);
    }


    private Mono<User> populateUserAccounts(User user) {
        return bankAccountRepository
                .findByUserId(user.getId())
                .flatMap(this::populateTransactions)
                .collectList()
                .map(accounts -> {
                    user.setBankAccounts(accounts);
                    return user;
                })
                .thenReturn(user);
    }


    private Mono<BankAccount> populateTransactions(BankAccount bankAccount) {
        return transactionRepository.findByBankAccountId(bankAccount.getId())
                .collectList()
                .map(transactions -> {
                    bankAccount.withTransactions(transactions);
                    return bankAccount;
                });
    }
}
