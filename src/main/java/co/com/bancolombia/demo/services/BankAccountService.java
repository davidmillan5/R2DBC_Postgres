package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.domain.entities.BankAccount;
import co.com.bancolombia.demo.domain.entities.User;
import co.com.bancolombia.demo.domain.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    public Mono<BankAccount> createBankAccount(BankAccount account, User user) {
        return null;
    }

}



