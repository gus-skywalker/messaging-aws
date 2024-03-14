package com.lesmonades.messaging.controllers;

import com.amazonaws.services.docdbelastic.model.Status;
import com.lesmonades.messaging.domain.Account;
import com.lesmonades.messaging.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestParam String customerName, @RequestParam String customerEmail, @RequestParam String customerPhone) {
        return new ResponseEntity<>(accountService.createAccount(customerName, customerEmail, customerPhone), HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam int accountNumber, @RequestParam double amount) {
        accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam int accountNumber, @RequestParam double amount) {
        accountService.withdraw(accountNumber, amount);
    }

    @GetMapping("/balance")
    public double checkBalance(@RequestParam int accountNumber) {
        return accountService.checkBalance(accountNumber);
    }

}

