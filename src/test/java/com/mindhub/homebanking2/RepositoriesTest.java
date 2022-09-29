package com.mindhub.homebanking2;

import com.mindhub.homebanking2.Models.*;
import com.mindhub.homebanking2.Repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
//indican a Spring que debe escanear en busca de clases @Entity y configurar los repositorios JPA.
public class RepositoriesTest {
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CardRepository cardRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;

	// verifica que existan préstamos en la base de datos, primero busca todos los préstamos y luego verificar que la lista no esté vacía
	@Test
	public void existLoans(){
		List<Loan> loans = loanRepository.findAll();
		assertThat(loans,is(not(empty())));
// afirmar que no este vacia
	}
	//verifica que en la lista de los préstamos exista uno llamado “Personal”
	@Test
	public void existPersonalLoan(){
		List<Loan> loans = loanRepository.findAll();
		assertThat(loans, hasItem(hasProperty("name", is("personal"))));
//verificar  que en la lista de prestamos exista una llamado personal
	}
	@Test
	public void existTypeLoan(){
		List<Loan> loans = loanRepository.findAll();
		assertThat(loans, hasItem(hasProperty("name", is("mortgage"))));
		assertThat(loans, hasItem(hasProperty("name", is("automotive"))));
	}
	@Test
	public void existClients() {
		List<Client> clients = clientRepository.findAll();
		assertThat(clients,is(not(empty())));
	}
	@Test
	public void existClientName() {
		List<Client> clients = clientRepository.findAll();
		assertThat(clients, hasItem(hasProperty("firstName", is("Melba"))));
		assertThat(clients, hasItem(hasProperty("firstName", is("Jero"))));
	}
	@Test
	public void existCards(){
		List<Card> cards = cardRepository.findAll();
		assertThat(cards,is(not(empty())));
	}
	@Test
	public void existCardColor() {
		List<Card> cards = cardRepository.findAll();
		assertThat(cards, hasItem(hasProperty("color", is(CardColor.GOLD))));
		assertThat(cards, hasItem(hasProperty("color", is(CardColor.TITANIUM))));
		assertThat(cards, hasItem(hasProperty("color", is(CardColor.SILVER))));
	}
	public void existCardCvv(){
		List<Card> cards = cardRepository.findAll();
		assertThat(cards, hasItem(hasProperty("cvv", hasToString(hasLength(3)))));
	}
	@Test
	public void existAccount() {
		List<Account> accounts = accountRepository.findAll();
		assertThat(accounts,is(not(empty())));
	}

	@Test
	public void existNumber(){
		List<Account> accounts = accountRepository.findAll();
		assertThat(accounts, hasItem(hasProperty("number", startsWith("VIN"))));
	}
	@Test
	public void existTransaction(){
		List<Transaction> transactions = transactionRepository.findAll();
		assertThat(transactions,is(not(empty())));
	}
	@Test
	public void existTransactionTypes() {
		List<Transaction> transactions = transactionRepository.findAll();
		assertThat(transactions, hasItem(hasProperty("type", is(TransactionType.CREDIT))));
		assertThat(transactions, hasItem(hasProperty("type", is(TransactionType.DEBIT))));
	}
}