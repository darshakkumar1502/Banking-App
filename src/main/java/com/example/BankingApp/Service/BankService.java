package com.example.BankingApp.Service;

import com.example.BankingApp.Model.BankAccount;
import com.example.BankingApp.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public void openAccount(BankAccount bank){
        double defaultBalance = 10000.0;
        bank.setBalance(defaultBalance + bank.getBalance());
        bankRepository.save(bank);
    }

     public void withDraw(Long accountNumber, double amount) throws Exception {
         BankAccount bank = bankRepository.findBankAccountByAcNumber(accountNumber);
         if (bank == null) {
             throw new Exception("Account not found.");
         }
         if (bank.getBalance() < amount) {
             throw new Exception("Insufficient balance.");
         }
         bank.setBalance(bank.getBalance() - amount);
         bankRepository.save(bank);
     }

     public void Deposit(Long accountNumber, double amount) throws Exception {
        BankAccount bank = bankRepository.findBankAccountByAcNumber(accountNumber);
        if (bank == null) {
            throw new Exception("Account not found.");
        }
        bank.setBalance(bank.getBalance() + amount);
        bankRepository.save(bank);
     }

     public void transfer(Long sourceAccountNumber, Long targetAccountNumber, double amount) throws Exception {
         BankAccount sourceAccount = bankRepository.findBankAccountByAcNumber(sourceAccountNumber);
         BankAccount targetAccount = bankRepository.findBankAccountByAcNumber(targetAccountNumber);

         if (sourceAccount == null) {
             throw new Exception("Source account not found.");
         }
         if (targetAccount == null) {
             throw new Exception("Target account not found.");
         }

         if (sourceAccount.getBalance() < amount) {
             throw new Exception("Insufficient balance.");
         }

         sourceAccount.setBalance(sourceAccount.getBalance() - amount);
         targetAccount.setBalance(targetAccount.getBalance() + amount);

         List<BankAccount> bankAccounts = new ArrayList<>();
         bankAccounts.add(sourceAccount);
         bankAccounts.add(targetAccount);

        bankRepository.saveAll(bankAccounts);
     }

     public BankAccount getBankByAcNumber(Long acNumber) {
        if (bankRepository.findBankAccountByAcNumber(acNumber) != null) {
            return bankRepository.findBankAccountByAcNumber(acNumber);
        }
        return null;
     }

     public List<BankAccount> getAllBankAccounts() {
        return bankRepository.findAll();
     }

     public void DeleteAccount(Long accountNumber) throws Exception {
        BankAccount bank = bankRepository.findBankAccountByAcNumber(accountNumber);
        if (bank == null) {
            throw new Exception("Account not found.");
        }
        bankRepository.delete(bank);
     }

     public String getExternalApi(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dogapi.dog/api/v2/breeds";
        return restTemplate.getForObject(url, String.class);
    }
}
