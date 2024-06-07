package co.com.bancolombia.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(name = "Transaction")
public class Transaction {

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    @Id
    private Long id;

    private double amount;

    private TransactionType type;

    private Date date;

    private BankAccount bankAccount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Transaction() {
    }


    public Transaction(Long id, double amount, TransactionType type, Date date, BankAccount bankAccount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
