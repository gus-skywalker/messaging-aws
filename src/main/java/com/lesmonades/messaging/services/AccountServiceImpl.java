package com.lesmonades.messaging.services;

import com.lesmonades.messaging.domain.Account;
import com.lesmonades.messaging.domain.Customer;
import com.lesmonades.messaging.domain.DTO.MessageDTO;
import com.lesmonades.messaging.domain.Sequence;
import com.lesmonades.messaging.repositories.AccountRepository;
import com.lesmonades.messaging.repositories.CustomerRepository;
import com.lesmonades.messaging.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final SequenceRepository sequenceRepository;

    private final AwsSnsService awsSnsService;

    @Override
    public Account createAccount(String customerName, String customerEmail, String customerPhone) {
        Customer customer = new Customer(customerName, customerEmail, customerPhone);
        customerRepository.save(customer);

        Sequence sequence = sequenceRepository.findByName("account_sequence");
        int accountNumber = sequence.getSeq();
        sequence.setSeq(sequence.getSeq() + 1);
        sequenceRepository.save(sequence);

        Account account = new Account(accountNumber, 0.0, customer);
        accountRepository.save(account);

        return account;
    }

    @Override
    public void removeAccount(int accountNumber) {
        Account account = accountRepository.findByNumber(accountNumber);
        if (account != null) {
            accountRepository.delete(account);
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

    @Override
    public Account findAccountById(int accountNumber) {
        Account account = accountRepository.findByNumber(accountNumber);
        if(account != null ) {
            return account;
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

    @Override
    public void deposit(int accountNumber, double amount) {
        Account account = accountRepository.findByNumber(accountNumber);
        if (account != null) {
            account.deposit(amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        Account account = accountRepository.findByNumber(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

    @Override
    public double checkBalance(int accountNumber) {
        Account account = accountRepository.findByNumber(accountNumber);
        if (account != null) {
            // Publish on AWS
            awsSnsService.publish(new MessageDTO(account.toString()));
            System.out.println(account);

            return account.getBalance();
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

}

