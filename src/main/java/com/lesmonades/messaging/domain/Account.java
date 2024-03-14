package com.lesmonades.messaging.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private int number;
    private double balance;
    private Customer holder;

    public Account(int number, double initialBalance, Customer holder) {
        this.number = number;
        this.balance = initialBalance;
        this.holder = holder;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("number", this.number);
        json.put("balance", this.balance);
        json.put("holder", this.holder);
        return json.toString();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Valor inválido para depósito.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Valor inválido para saque.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }

    public Customer getHolder() {
        return holder;
    }
}