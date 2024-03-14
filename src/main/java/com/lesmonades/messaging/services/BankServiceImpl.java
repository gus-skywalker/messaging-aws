package com.lesmonades.messaging.services;

import com.lesmonades.messaging.domain.Account;
import com.lesmonades.messaging.domain.Bank;
import com.lesmonades.messaging.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final AccountService accountService;

    @Override
    public Bank createBank(String name) {
        Bank bank = new Bank(name);
        bankRepository.save(bank);
        return bank;
    }

    @Override
    public void addAccount(String bankId, int accountId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        Account newAccount = accountService.findAccountById(accountId);
        if( newAccount != null ) {
            bank.addAccount(newAccount);
        } else {
            throw new IllegalArgumentException("Account not found!");
        }
        bankRepository.save(bank);
    }

    @Override
    public void removeAccount(String bankId, int accountNumber) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        bank.removeAccount(accountNumber);
        bankRepository.save(bank);
    }

    @Override
    public Account findAccount(String bankId, int accountNumber) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        return bank.findAccount(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts(String bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        return bank.getAllAccounts();
    }
}

