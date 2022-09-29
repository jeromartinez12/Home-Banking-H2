package com.mindhub.homebanking2.dtos;
import com.mindhub.homebanking2.Models.Card;
import com.mindhub.homebanking2.Models.CardColor;
import com.mindhub.homebanking2.Models.CardType;
import java.time.LocalDate;

public class CardDTO {
    private Long id;
    private String cardholder;
    private CardColor color;
    private CardType type;
    private String number;
    private int cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;
    private Boolean active;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardholder = card.getCardholder();
        this.color = card.getColor();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.active = card.getActive();
    }

    public Long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public CardColor getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public Boolean getActive() {
        return active;
    }
}
