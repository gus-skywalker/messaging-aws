package com.lesmonades.messaging.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Document(collection = "banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    private String id;
    private String name;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(int accountNumber) {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getNumber() == accountNumber) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("Conta não encontrada.");
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return account;
            }
        }
        throw new IllegalArgumentException("Conta não encontrada.");
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}


