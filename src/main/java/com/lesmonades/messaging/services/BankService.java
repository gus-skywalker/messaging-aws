package com.lesmonades.messaging.services;

import com.lesmonades.messaging.domain.Account;
import com.lesmonades.messaging.domain.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {
    Bank createBank(String name);
    void addAccount(String bankId, int account);
    void removeAccount(String bankId, int accountNumber);
    Account findAccount(String bankId, int accountNumber);
    List<Account> getAllAccounts(String bankId);
}
