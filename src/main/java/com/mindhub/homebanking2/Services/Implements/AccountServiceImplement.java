package com.mindhub.homebanking2.Services.Implements;

import com.mindhub.homebanking2.Models.Account;
import com.mindhub.homebanking2.Repositories.AccountRepository;
import com.mindhub.homebanking2.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImplement implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public void saveAccount(Account account){
		accountRepository.save(account);
	}
	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id).orElse(null);
	}
	@Override
	public Account findByNumber(String number){
		return accountRepository.findByNumber(number);
	}
	@Override
	public void stopAccount(Account account) {
		account.setActive(false);
		accountRepository.save(account);
	}
}
