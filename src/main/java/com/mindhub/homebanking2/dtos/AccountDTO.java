package com.mindhub.homebanking2.dtos;

import com.mindhub.homebanking2.Models.Account;
import com.mindhub.homebanking2.Models.AccountType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private  Long id;
    private  String number;
    private LocalDateTime creationDate;
    private Double balance;

    private Set<TransactionDTO> transaction;

    private Boolean active;

    private AccountType accountType;

    public AccountDTO() {
    }
    //Metodo que recibe de parametro Account account (
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transaction = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.active = account.getActive();
        this.accountType = account.getAccountType();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public Set<TransactionDTO> getTransaction() {
        return transaction;
    }

    public Boolean getActive() {
        return active;
    }
    public AccountType getAccountType() {
        return accountType;
    }
}

