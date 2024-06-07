package co.com.bancolombia.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name = "BankAccount")
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

    private User user;

    private List<Transaction> transactions;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BankAccount() {
    }

    public BankAccount(Long id, AccountType accountType, double balance, User user, List<Transaction> transactions) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
        this.transactions = transactions;
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", user=" + user +
                ", transactions=" + transactions +
                '}';
    }
}
