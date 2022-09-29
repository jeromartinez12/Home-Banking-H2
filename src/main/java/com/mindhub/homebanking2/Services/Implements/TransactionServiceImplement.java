package com.mindhub.homebanking2.Services.Implements;

import com.mindhub.homebanking2.Models.Account;
import com.mindhub.homebanking2.Models.Transaction;
import com.mindhub.homebanking2.Repositories.AccountRepository;
import com.mindhub.homebanking2.Repositories.TransactionRepository;
import com.mindhub.homebanking2.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {

	@Autowired //INYECTAMOS DE DEPENDENCIAS (EN ESTE CASO EL REPOSITORIO)
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override //SOBREESCRIBIMOS EL METODO
	public void saveTransaction(Transaction transaction){
		transactionRepository.save(transaction);
	}

	@Override
	public Set<Transaction> getTransactionByAccount (String account) {
		return accountRepository.findByNumber(account).getTransactions();
	}
	@Override
	public Set<Transaction> filterTransactionsWithDate(LocalDateTime fromDate, LocalDateTime toDate, Account account){
		return transactionRepository.findByDateBetween(fromDate, toDate).stream().filter(transaction -> transaction.getAccount() == account).collect(Collectors.toSet());
	}
}
