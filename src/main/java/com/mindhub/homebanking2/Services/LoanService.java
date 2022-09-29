package com.mindhub.homebanking2.Services;

import com.mindhub.homebanking2.Models.Loan;

import java.util.List;

public interface LoanService {
	Loan getLoanById(Long id);

	Loan getLoanByName(String name);

	List<Loan> getAllLoans();

	void saveLoan(Loan loan);
}
