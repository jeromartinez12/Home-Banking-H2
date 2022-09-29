package com.mindhub.homebanking2.dtos;
import com.mindhub.homebanking2.Models.Transaction;
import com.mindhub.homebanking2.Models.TransactionType;
import java.time.LocalDateTime;

//DTO: es la forma en la que se transfieren los datos del objeto.
public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime date;

    private Double balance;

    public TransactionDTO() {
    }

    //Por parametros le pasamos el objeto transaction para obtener la información.
    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.balance = transaction.getBalance();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }
}
