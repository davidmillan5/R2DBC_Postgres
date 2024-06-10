package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.Transaction;
import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import co.com.bancolombia.demo.domain.repositories.TransactionRepository;
import co.com.bancolombia.demo.services.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public Mono<Transaction> createTransaction(Transaction transaction, Long accountId) {
        return bankAccountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new RuntimeException("Bank account not found")))
                .flatMap(bankAccount -> {
                    transaction.setBankAccount(bankAccount);
                    return transactionRepository.save(transaction);
                });
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction not found")));
    }

    @Override
    @Transactional
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return transactionRepository.findById(transaction.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction not found")))
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
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction not found")))
                .flatMap(transactionRepository::delete);
    }
}
