package com.mindhub.homebanking2.Services;

import com.mindhub.homebanking2.Models.Account;
import com.mindhub.homebanking2.Models.Transaction;

import java.time.LocalDateTime;
import java.util.Set;

public interface TransactionService {
	void saveTransaction(Transaction transaction);

	Set<Transaction> getTransactionByAccount(String account);

	Set<Transaction> filterTransactionsWithDate(LocalDateTime fromDate, LocalDateTime toDate, Account account);
}
