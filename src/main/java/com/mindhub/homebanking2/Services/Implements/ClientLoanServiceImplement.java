package com.mindhub.homebanking2.Services.Implements;

import com.mindhub.homebanking2.Models.ClientLoan;
import com.mindhub.homebanking2.Repositories.ClientLoanRepository;
import com.mindhub.homebanking2.Services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {

	@Autowired
	ClientLoanRepository clientLoanRepository;

	@Override
	public void saveLoan(ClientLoan clientLoan){
		clientLoanRepository.save(clientLoan);
	}
}
