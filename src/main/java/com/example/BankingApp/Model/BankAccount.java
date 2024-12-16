package com.example.BankingApp.Model;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ac_Number", unique = true, nullable = false)
    private Long acNumber;

    @Column(name = "balance")
    private double balance;


    public Long generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String accNo = uuid.substring(0,10);
        return Long.parseLong(accNo,16) % 10000000000L;
    }

    public BankAccount() {
        this.acNumber = generateAccountNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAcNumber() {
        return acNumber;
    }

    public void setAcNumber(Long acNumber) {
        this.acNumber = acNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
