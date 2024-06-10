package co.com.bancolombia.demo.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "bank_accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BankAccount {

    public enum AccountType {
        SAVINGS,
        CHECKING,
        MONEY,
        SALARY,
        CURRENCY,
        STUDENTS,
        CDS
    }

    @Id
    private Long id;

    private AccountType accountType;

    private double balance;

    private Long userId;

    @Transient
    private List<Transaction> transactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public BankAccount withTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public BankAccount() {
    }

    public BankAccount(Long id, AccountType accountType, double balance, Long userId, List<Transaction> transactions) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", userId=" + userId +
                ", transactions=" + transactions +
                '}';
    }
}
