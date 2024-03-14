package com.lesmonades.messaging.services;

import com.lesmonades.messaging.domain.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account createAccount(String customerName, String customerEmail, String customerPhone);
    void removeAccount(int accountNumber);

    Account findAccountById(int accountNumber);
    void deposit(int accountNumber, double amount);

    void withdraw(int accountNumber, double amount);

    double checkBalance(int accountNumber);
}
