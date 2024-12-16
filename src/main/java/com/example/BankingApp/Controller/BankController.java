package com.example.BankingApp.Controller;

import com.example.BankingApp.Model.BankAccount;
import com.example.BankingApp.Model.ResponseDTO;
import com.example.BankingApp.Model.TransferRequest;
import com.example.BankingApp.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bankAccount")
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("openAccount")
    public void CreateAccount(@RequestBody BankAccount bank) {
        bankService.openAccount(bank);
    }

    @PostMapping("withdraw")
    public ResponseEntity<String> withdraw(@RequestBody BankAccount bank) {
        try {
            Long accountNumber = bank.getAcNumber();
            double amount = bank.getBalance();
            bankService.withDraw(accountNumber, amount);
            return ResponseEntity.ok("Withdrawal successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("deposit")
    public ResponseEntity<String> deposit(@RequestBody BankAccount bank) {
        try {
            Long accountNumber = bank.getAcNumber();
            double amount = bank.getBalance();
            bankService.Deposit(accountNumber, amount);
            return ResponseEntity.ok("deposite successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            bankService.transfer(
                    transferRequest.getSourceAccountNumber(),
                    transferRequest.getTargetAccountNumber(),
                    transferRequest.getAmount()
            );
            return ResponseEntity.ok("Transaction successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get")
    public BankAccount getBank(@RequestHeader(name = "acNumber") Long acNumber) {
       return bankService.getBankByAcNumber(acNumber);
    }

    @GetMapping("getAll")
    public ResponseDTO getAll(){
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<BankAccount> bankAccountList = bankService.getAllBankAccounts();
            responseDTO.setBody(bankAccountList);
            responseDTO.setHttpStatus(HttpStatus.OK);
            responseDTO.setMessage("students get successfully");
        } catch (Exception e){
           responseDTO.setBody(null);
           responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
           responseDTO.setMessage("request failed");
        }
        return responseDTO;
    }

    @DeleteMapping("deleteAccount")
    public ResponseEntity<String> deleteAccount(@RequestHeader(name = "acNumber") Long acNumber) throws Exception {
        try
        {
            bankService.DeleteAccount(acNumber);
            return ResponseEntity.ok("Delete successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("fetch")
    public String fetchBank() {
        return bankService.getExternalApi();
    }

}
