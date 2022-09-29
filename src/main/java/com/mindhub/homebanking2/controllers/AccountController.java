package com.mindhub.homebanking2.controllers;

import com.mindhub.homebanking2.Models.*;
import com.mindhub.homebanking2.Services.AccountService;
import com.mindhub.homebanking2.Services.ClientService;
import com.mindhub.homebanking2.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    //servlet
    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountService.getAllAccounts().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    //servlet: son clases Java diseñadas para responder a solicitudes HTTP.
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return new AccountDTO(accountService.getAccountById(id));
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> newAccount (Authentication authentication, @RequestParam AccountType accountType) {

        String accountNumber = "VIN - " + getRandomNumber(1, 99999999);

        Client client = clientService.findClientByEmail(authentication.getName());

        if (client == null){
            return new ResponseEntity<>("Client doesn't exist", HttpStatus.FORBIDDEN);

        }

        if (client.getAccounts().stream().filter(Account::getActive).count() >= 3) {
            return new ResponseEntity<>("You cannot have more than 3 accounts", HttpStatus.FORBIDDEN);
        }

        if (accountType == null){
            return new ResponseEntity<>("Complete account type, please", HttpStatus.FORBIDDEN);
        }

        accountService.saveAccount(new Account(accountNumber, LocalDateTime.now(), 0.0, client, true, accountType));

        return new ResponseEntity<>("Your new account was created successfully",HttpStatus.CREATED);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PatchMapping("/clients/current/accounts/stopaccount")
    public ResponseEntity<Object> stopAccount(@RequestParam Long accountId, Authentication authentication) {

        Client client = clientService.findClientByEmail(authentication.getName());

        Account account = accountService.getAccountById(accountId);

        if(account == null){
            return new ResponseEntity<>("The account doesn't exist", HttpStatus.FORBIDDEN);
        }

        if(account.getBalance() > 0) {
            return new ResponseEntity<>("The account has money and cannot be deleted", HttpStatus.FORBIDDEN);
        }

        if(client == null){
            return new ResponseEntity<>("Client doesn't exist", HttpStatus.FORBIDDEN);
        }
        //Para que el cliente no pueda borrar una tarjeta que no es suya
        if(!client.getAccounts().contains(account)){
            return new ResponseEntity<>("Client doesn't exist", HttpStatus.FORBIDDEN);
        }

        accountService.stopAccount(account);

        return new ResponseEntity<>("Your account is paused", HttpStatus.ACCEPTED);
    }

}

