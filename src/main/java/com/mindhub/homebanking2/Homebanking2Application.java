package com.mindhub.homebanking2;

import com.mindhub.homebanking2.Models.*;

import com.mindhub.homebanking2.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking2.Models.TransactionType.CREDIT;
import static com.mindhub.homebanking2.Models.TransactionType.DEBIT;

@SpringBootApplication
public class Homebanking2Application {

	public static void main(String[] args) {
		SpringApplication.run(Homebanking2Application.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientService clientService, AccountService accountService, TransactionService transactionService, ClientLoanService clientLoanService, LoanService loanService, CardService cardService) {
		return (args) -> {

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melbamorel123"));
			Account account1 = new Account("VIN-001", LocalDateTime.now(), 5000.0, client1, true, AccountType.CURRENT);
			Account account2 = new Account("VIN-002", LocalDateTime.now().plusDays(1), 7500.0, client1, true, AccountType.SAVING);

			Client client2 = new Client("Jero", "Morel", "jeromorel@mindhub.com", passwordEncoder.encode("jeromorel123"));
			Account account3 = new Account("VIN-003", LocalDateTime.now(), 5000.0, client2, true, AccountType.SAVING);
			Account account4 = new Account("VIN-004", LocalDateTime.now().plusDays(1), 7500.0, client2, true, AccountType.CURRENT);

			Client admin1 = new Client("admin", "admin", "admin@mindhub.com", passwordEncoder.encode("admin123"));

			Transaction transaction1 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now().plusMonths(-3), account1);
			Transaction transaction2 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now().plusMonths(-2), account1);
			Transaction transaction13 = new Transaction(CREDIT, 5000.0, "TR 1", LocalDateTime.now().plusMonths(-1), account1);
			Transaction transaction14 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now().plusDays(-5), account1);
			Transaction transaction5 = new Transaction(CREDIT, 2060.0, "TR 1", LocalDateTime.now().plusDays(-10), account1);
			Transaction transaction6 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now().plusDays(-10), account1);
			Transaction transaction7 = new Transaction(CREDIT, 2050.0, "TR 1", LocalDateTime.now().plusDays(-1), account1);
			Transaction transaction8 = new Transaction(DEBIT, -2600.0, "TR 1", LocalDateTime.now().plusDays(-1), account1);
			Transaction transaction9 = new Transaction(DEBIT, -2300.0, "TR 1", LocalDateTime.now().plusDays(-2), account1);
			Transaction transaction10 = new Transaction(CREDIT, 2400.0, "TR 1", LocalDateTime.now().plusDays(-8), account1);
			Transaction transaction11 = new Transaction(DEBIT, -2050.0, "TR 1", LocalDateTime.now().plusDays(-9), account1);
			Transaction transaction12 = new Transaction(CREDIT, 3040.0, "TR 2", LocalDateTime.now().plusDays(-2), account1);

			Transaction transaction21 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now(), account2);
			Transaction transaction20 = new Transaction(CREDIT, 3000.0, "TR 2", LocalDateTime.now(), account2);

			Transaction transaction3 = new Transaction(DEBIT, -2000.0, "TR 1", LocalDateTime.now(), account3);
			Transaction transaction4 = new Transaction(CREDIT, 3000.0, "TR 2", LocalDateTime.now(), account4);

			Loan mortgage = new Loan("mortgage", 500000.00, List.of(12, 24, 36, 48, 60), 20.00);
			Loan personal = new Loan("personal", 100000.00, List.of(6, 12, 24), 15.00);
			Loan automotive = new Loan("automotive", 300000.00, List.of(6, 12, 24, 36), 17.00);
			Loan scholar = new Loan("Scholar", 300000.00, List.of(6, 12, 24, 36), 13.00);

			ClientLoan clientLoan1 = new ClientLoan(400000.0, 60, client1, mortgage);
			ClientLoan clientLoan2 = new ClientLoan(50000.0, 12, client1, personal);

			ClientLoan clientLoan3 = new ClientLoan(10000.0, 24, client2, personal);
			ClientLoan clientLoan4 = new ClientLoan(200000.0, 36, client2, automotive);

			Card card1 = new Card((client1.getFirstName() + " " + client1.getLastName()), CardColor.GOLD, CardType.DEBIT, "4254-1234-1234-1234", 143, LocalDate.now().plusDays(-1), LocalDate.now(), client1, true);
			Card card2 = new Card((client1.getFirstName() + " " + client1.getLastName()), CardColor.TITANIUM, CardType.CREDIT, "4321-1234-4321-1364", 523, LocalDate.now().plusYears(5), LocalDate.now(), client1, true);
			Card card3 = new Card((client2.getFirstName() + " " + client2.getLastName()), CardColor.SILVER, CardType.CREDIT, "5674-8765-5678-2341", 451, LocalDate.now().plusYears(5), LocalDate.now(), client2, true);
			Card card4 = new Card((client2.getFirstName() + " " + client2.getLastName()), CardColor.GOLD, CardType.DEBIT, "6274-4243-4321-2442", 751, LocalDate.now().plusYears(5), LocalDate.now(), client2, true);

			clientService.saveClient(client1);
			clientService.saveClient(client2);
			clientService.saveClient(admin1);

			accountService.saveAccount(account1);
			accountService.saveAccount(account2);
			accountService.saveAccount(account3);
			accountService.saveAccount(account4);

			transactionService.saveTransaction(transaction1);
			transactionService.saveTransaction(transaction2);
			transactionService.saveTransaction(transaction5);
			transactionService.saveTransaction(transaction6);
			transactionService.saveTransaction(transaction7);
			transactionService.saveTransaction(transaction8);
			transactionService.saveTransaction(transaction9);
			transactionService.saveTransaction(transaction10);
			transactionService.saveTransaction(transaction11);
			transactionService.saveTransaction(transaction12);
			transactionService.saveTransaction(transaction13);
			transactionService.saveTransaction(transaction14);

			transactionService.saveTransaction(transaction20);
			transactionService.saveTransaction(transaction21);


			transactionService.saveTransaction(transaction3);
			transactionService.saveTransaction(transaction4);

			loanService.saveLoan(mortgage);
			loanService.saveLoan(personal);
			loanService.saveLoan(automotive);
			loanService.saveLoan(scholar);

			clientLoanService.saveLoan(clientLoan1);
			clientLoanService.saveLoan(clientLoan2);
			clientLoanService.saveLoan(clientLoan3);
			clientLoanService.saveLoan(clientLoan4);

			cardService.saveCard(card1);
			cardService.saveCard(card2);
			cardService.saveCard(card3);
			cardService.saveCard(card4);
		};
	}

}

