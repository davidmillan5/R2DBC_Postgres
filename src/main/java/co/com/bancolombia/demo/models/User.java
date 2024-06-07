package co.com.bancolombia.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Persistable<Long> {

    @Id
    private Long id;

    private String name;

    private List<BankAccount> bankAccounts;

    @Transient
    private boolean newUser;


    @Override
    public boolean isNew() {
        return this.newUser || id == null;
    }

    public User setAsNew(){
        this.newUser = true;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }


    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


    public User() {
    }

    public User(Long id, String name, List<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}
