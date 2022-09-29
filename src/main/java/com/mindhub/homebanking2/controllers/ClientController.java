package com.mindhub.homebanking2.controllers;

import com.mindhub.homebanking2.Models.Account;
import com.mindhub.homebanking2.Models.AccountType;
import com.mindhub.homebanking2.Models.Client;
import com.mindhub.homebanking2.Services.AccountService;
import com.mindhub.homebanking2.Services.ClientService;
import com.mindhub.homebanking2.dtos.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") //asociamos la ruta a la peticion
public class ClientController {

    @Autowired  // generamos una instancia del repositorio (el concepto se llama: inyeccion de dependencia)
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientService.getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
        //STREAM: es una colección de datos para poder mapear
    };

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(clientService.getClientById(id));
    }

    @GetMapping("/clients/current")
    public ClientDTO getAll (Authentication authentication) {
        return new ClientDTO(clientService.findClientByEmail(authentication.getName()));
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(
    //ResponseEntity<object> : es para poder manipular las propiedades de las respuestas.
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        String accountNumber = "VIN - " + getRandomNumber(1, 99999999);

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Complete all fields", HttpStatus.FORBIDDEN);
        }
        if (clientService.findClientByEmail(email) != null) {
             return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(newClient);
        accountService.saveAccount(new Account(accountNumber, LocalDateTime.now(), 0.0, newClient, true, AccountType.SAVING));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
