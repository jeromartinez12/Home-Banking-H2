package com.mindhub.homebanking2.dtos;

import com.mindhub.homebanking2.Models.ClientLoan;

public class ClientLoanDTO {
    private long id;
    private Long loanId;
    private Double Amount;
    private Integer payments;
    private String name;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        Amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.name = clientLoan.getLoan().getName();
    }

    public long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Double getAmount() {
        return Amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getName() {
        return name;
    }


}
