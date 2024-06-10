package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import co.com.bancolombia.demo.domain.repositories.UserRepository;
import co.com.bancolombia.demo.exceptions.BankAccountNotFoundException;
import co.com.bancolombia.demo.exceptions.InvalidUserException;
import co.com.bancolombia.demo.services.BankAccountService;
import co.com.bancolombia.demo.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountImpl implements BankAccountService {

    private final UserService userService;
    private final BankAccountRepository bankAccountRepository;
    private final WebClient client;

    public BankAccountImpl(UserRepository userRepository, UserService userService, BankAccountRepository bankAccountRepository, WebClient.Builder builder) {
        this.userService = userService;
        this.bankAccountRepository = bankAccountRepository;
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    @Override
    @Transactional
    public Mono<BankAccount> createBankAccount(BankAccount account) {
        return userService.getUserById(account.getUserId())
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnNext(System.out::println)
                .onErrorMap(throwable -> new InvalidUserException("You need to create a valid user in order to create an account"))
                .flatMap((user) -> client.get()
                        .uri("/validate/user/{userId}",user.getId())
                        .retrieve()
                        .bodyToMono(String.class)
                        .doOnNext(System.out::println)
                        .flatMap(result -> switch(result){
                            case "user does not exist",
                                 "you should create a valid user" -> Mono.error(new InvalidUserException(result));
                            case "user exists" ->   bankAccountRepository.save(account);
                            default -> Mono.error(new InvalidUserException("Invalid result"));
                        }));
    }

    @Override
    public Flux<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new BankAccountNotFoundException("Bank account not found")));
    }

    @Override
    @Transactional
    public Mono<BankAccount> updateBankAccount(BankAccount account) {
        return bankAccountRepository.findById(account.getId())
                .switchIfEmpty(Mono.error(new BankAccountNotFoundException("Bank account not found")))
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
                .switchIfEmpty(Mono.error(new BankAccountNotFoundException("Bank account not found")))
                .flatMap(bankAccountRepository::delete);
    }
}
