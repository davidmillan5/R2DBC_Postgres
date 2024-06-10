package co.com.bancolombia.demo.services;

import reactor.core.publisher.Mono;

public interface ValidateBankAccountService {
    Mono<String>validateBankAccount(Long accountId);
}
