package com.mindhub.homebanking2.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mindhub.homebanking2.Models.*;
import com.mindhub.homebanking2.Services.AccountService;
import com.mindhub.homebanking2.Services.ClientService;
import com.mindhub.homebanking2.Services.TransactionService;
import com.mindhub.homebanking2.dtos.PdfDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static com.mindhub.homebanking2.Models.TransactionType.CREDIT;
import static com.mindhub.homebanking2.Models.TransactionType.DEBIT;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private TransactionService transactionService;

	@Transactional
	@PostMapping("/clients/current/transactions")
	public ResponseEntity<Object> newTransaction(@RequestParam Double amount, @RequestParam String description, @RequestParam String accountOrigin, @RequestParam String accountDestiny, Authentication authentication) {
	//ResponseEntity<object> : es para poder manipular las propiedades de las respuestas.
		Client newClientAuthentication = clientService.findClientByEmail(authentication.getName());
		Account newAccountOrigin = accountService.findByNumber(accountOrigin);
		Account newAccountDestiny = accountService.findByNumber(accountDestiny);

		if (newClientAuthentication == null) {
			return new ResponseEntity<>("The Client doesn't exist", HttpStatus.FORBIDDEN);
		}

		if (amount <= 0 ){
			return new ResponseEntity<>("Invalid amount", HttpStatus.FORBIDDEN);
		}
		if (description.isEmpty() || accountOrigin.isEmpty() || accountDestiny.isEmpty()) {
			return new ResponseEntity<>("Same accounts", HttpStatus.FORBIDDEN);
		}
		if (newAccountOrigin.getActive() == null){
			return new ResponseEntity<>("Origin account doesn't exist", HttpStatus.FORBIDDEN);
		}
		if (newAccountDestiny.getActive() == null) {
			return new ResponseEntity<>("Destiny account doesn't exist", HttpStatus.FORBIDDEN);
		}

		if (accountOrigin.equals(accountDestiny)) {
			return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
		}
		if (newAccountOrigin == null){
			return new ResponseEntity<>("Origin account is null", HttpStatus.FORBIDDEN);
		}
		if (newAccountDestiny == null){
			return new ResponseEntity<>("Destiny account is null", HttpStatus.FORBIDDEN);
		}
		if(accountService.findByNumber(accountOrigin).getBalance() < amount){
			return new ResponseEntity<>("Not enough money", HttpStatus.FORBIDDEN);
		}

		Double balance1 = newAccountOrigin.getBalance() - amount;
		Double balance2 = newAccountDestiny.getBalance() + amount;

		Transaction debitTransaction = new Transaction(DEBIT, - amount,"Description: " + description + " -> Go to: " + accountDestiny, LocalDateTime.now(), balance1, newAccountOrigin);
		Transaction creditTransaction = new Transaction(CREDIT, amount, "Description: " + description + " -> from: " + accountOrigin, LocalDateTime.now(), balance2, newAccountDestiny);

		transactionService.saveTransaction(debitTransaction);
		transactionService.saveTransaction(creditTransaction);

		newAccountOrigin.setBalance(newAccountOrigin.getBalance() - amount);
		newAccountDestiny.setBalance(newAccountDestiny.getBalance() + amount);

		accountService.saveAccount(newAccountOrigin);
		accountService.saveAccount(newAccountDestiny);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/transactions/filtered")
	public ResponseEntity<Object> getFilteredTransaction(
			@RequestBody PdfDTO pdfDTO, Authentication authentication) throws DocumentException, FileNotFoundException {
		Client client = clientService.findClientByEmail(authentication.getName());
		Account account = accountService.findByNumber(pdfDTO.getClientAccount());

		if(!client.getAccounts().contains(account)){
			return new ResponseEntity<>("You cannot request data from an account that isn't yours.", HttpStatus.FORBIDDEN);
		}
		if (account.getTransactions()==null){
			return new ResponseEntity<>("You don't have any transactions in this account.", HttpStatus.FORBIDDEN);
		}

		Set<Transaction> transactions = transactionService.filterTransactionsWithDate(pdfDTO.getFromDate(),pdfDTO.getToDate(),account);
		createPdf( transactions);
		return new ResponseEntity<>("Transactions",HttpStatus.CREATED);
	}

	public static void createPdf(Set<Transaction> transactions) throws DocumentException, FileNotFoundException {
		var doc = new Document();
		String route = System.getProperty("user.home");
		PdfWriter.getInstance(doc, new FileOutputStream(route + "/Desktop/TransactionInfo.pdf"));
		doc.open();

		var bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		var paragraph = new Paragraph("MindHub Bank");
		var table = new PdfPTable(4);
		Stream.of("Amount", "Description","Date","Type").forEach(table::addCell);

		transactions.forEach(transaction -> {
			table.addCell("$" +transaction.getAmount());
			table.addCell(transaction.getDescription());
			table.addCell(transaction.getDate().toString());
			table.addCell(transaction.getType().toString());
		});

		paragraph.add(table);
		doc.add(paragraph);
		doc.close();
	}

}

