package com.mindhub.homebanking2.Services.Implements;


import com.mindhub.homebanking2.Models.Loan;
import com.mindhub.homebanking2.Repositories.LoanRepository;
import com.mindhub.homebanking2.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanServiceImplement implements LoanService {
	@Autowired
	LoanRepository loanRepository;
	@Override
	public Loan getLoanById(Long id) {
		return loanRepository.findById(id).get();
	}

	@Override
	public Loan getLoanByName(String name) {
		return loanRepository.findByName(name);
	}

	@Override
	public List<Loan> getAllLoans(){
		return loanRepository.findAll();
	}
	@Override
	public void saveLoan(Loan loan) {loanRepository.save(loan);}

}
