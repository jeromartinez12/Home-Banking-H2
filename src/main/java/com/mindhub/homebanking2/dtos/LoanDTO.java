package com.mindhub.homebanking2.dtos;

import com.mindhub.homebanking2.Models.Loan;
import java.util.List;

public class LoanDTO {
	private Long id;
	private String name;
	private Double maxAmount;
	private List<Integer> payments;
	private Double interest;

	public LoanDTO() {
	}

	public LoanDTO(Loan loan) {
		this.id = loan.getId();
		this.name = loan.getName();
		this.maxAmount = loan.getMaxAmount();
		this.payments = loan.getPayments();
		this.interest = loan.getInterest();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public List<Integer> getPayments() {
		return payments;
	}

	public Double getInterest() {
		return interest;
	}
}
