package com.mindhub.homebanking2.Models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //Le dice a Spring que cree una tabla dentro de la base de datos.
public class Transaction {
    @Id //Dice que la variable (id) contiene la clave primaria de la base de datos.
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //Genera el valor del Id
    @GenericGenerator(name = "native", strategy = "native") //Hace que el ID generado no se repita en la base de datos
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER) //Relacion uno a muchos, se cargan los datos de las clases anotadas (Transaction y Account)
    @JoinColumn(name="account_id") //Dice que columna tiene el id de la cuenta
    private Account account;

    public Transaction() {
    }

    public Transaction(TransactionType type, Double amount, String description, LocalDateTime date, Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
    }

    public Transaction(TransactionType type, Double amount, String description, LocalDateTime date, Double balance, Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.balance = balance;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
