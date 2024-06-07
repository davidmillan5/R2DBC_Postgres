package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.models.BankAccount;
import co.com.bancolombia.demo.models.User;
import co.com.bancolombia.demo.repositories.BankAccountRepository;
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



