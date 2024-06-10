package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import co.com.bancolombia.demo.exceptions.BankAccountNotFoundException;
import co.com.bancolombia.demo.exceptions.UserNotFoundException;
import co.com.bancolombia.demo.services.ValidateBankAccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ValidateBankAccountServiceImpl implements ValidateBankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public ValidateBankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Mono<String> validateBankAccount(Long accountId) {
        return bankAccountRepository.existsById(accountId)
                .flatMap(exists -> {
            if (exists) {
                return Mono.just("Bank Account exists.");
            } else {
                return Mono.error(new BankAccountNotFoundException("Bank Account not found."));
            }
        });
    }
}
