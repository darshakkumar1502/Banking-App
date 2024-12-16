package com.example.BankingApp.Repository;

import com.example.BankingApp.Model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankAccount, Integer> {
    public BankAccount findBankAccountByAcNumber(Long acNumber);
}
