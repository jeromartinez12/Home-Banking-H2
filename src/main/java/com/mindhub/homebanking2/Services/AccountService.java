package com.mindhub.homebanking2.Services;

import com.mindhub.homebanking2.Models.Account;
import java.util.List;

public interface AccountService {
	void saveAccount(Account account);

	List<Account> getAllAccounts();

	Account getAccountById(Long id);

	Account findByNumber(String number);

	void stopAccount(Account account);
}
