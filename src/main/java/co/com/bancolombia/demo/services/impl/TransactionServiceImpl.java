package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.Transaction;
import co.com.bancolombia.demo.domain.repositories.TransactionRepository;
import co.com.bancolombia.demo.exceptions.InvalidBankAccountException;
import co.com.bancolombia.demo.exceptions.TransactionNotFoundException;
import co.com.bancolombia.demo.services.BankAccountService;
import co.com.bancolombia.demo.services.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final WebClient client;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BankAccountService bankAccountService, WebClient.Builder builder) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    @Override
    @Transactional
    public Mono<Transaction> createTransaction(Transaction transaction) {
        return bankAccountService.getBankAccountById(transaction.getBankAccountId())
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnNext(System.out::println)
                .onErrorMap(throwable -> new InvalidBankAccountException("You need to create a valid account in order to create a transaction"))
                .flatMap((account) -> client.get()
                        .uri("/validate/bankaccount/{bankAccountId}", account.getId())
                        .retrieve()
                        .bodyToMono(String.class)
                        .doOnNext(System.out::println)
                        .flatMap(result -> switch(result){
                            case "account does not exist",
                                "You should have a valid bank account" -> Mono.error(new InvalidBankAccountException(result));
                            case "Bank Account exists." -> transactionRepository.save(transaction);
                            default -> Mono.error(new InvalidBankAccountException(result));
                        }));
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")));
    }

    @Override
    @Transactional
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return transactionRepository.findById(transaction.getId())
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .flatMap(existingTransaction -> {
                    existingTransaction.setAmount(transaction.getAmount());
                    existingTransaction.setType(transaction.getType());
                    existingTransaction.setDate(transaction.getDate());
                    return transactionRepository.save(existingTransaction);
                });
    }

    @Override
    @Transactional
    public Mono<Void> deleteTransaction(Long id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new TransactionNotFoundException("Transaction not found")))
                .flatMap(transactionRepository::delete);
    }
}
