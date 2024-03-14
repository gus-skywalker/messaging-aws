package com.lesmonades.messaging.controllers;

import com.lesmonades.messaging.domain.Account;
import com.lesmonades.messaging.domain.Bank;
import com.lesmonades.messaging.services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody String name) {
        Bank createdBank = bankService.createBank(name);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @PostMapping("/{bankId}/accounts/{accountNumber}")
    public ResponseEntity<Void> addAccount(@PathVariable String bankId, @PathVariable int accountNumber) {
        bankService.addAccount(bankId, accountNumber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bankId}/accounts/{accountNumber}")
    public ResponseEntity<Void> removeAccount(@PathVariable String bankId, @PathVariable int accountNumber) {
        bankService.removeAccount(bankId, accountNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bankId}/accounts/{accountNumber}")
    public ResponseEntity<Account> findAccount(@PathVariable String bankId, @PathVariable int accountNumber) {
        Account account = bankService.findAccount(bankId, accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{bankId}/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String bankId) {
        List<Account> accounts = bankService.getAllAccounts(bankId);
        return ResponseEntity.ok(accounts);
    }
}

