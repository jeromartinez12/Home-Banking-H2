package com.mindhub.homebanking2.Services.Implements;

import com.mindhub.homebanking2.Models.Client;
import com.mindhub.homebanking2.Repositories.ClientRepository;
import com.mindhub.homebanking2.Services.ClientService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id).get();
	}
	@Override
	public Client findClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}
	@Override
	public void saveClient(Client client){
		clientRepository.save(client);
	}

	@Override
	public Client getClientCurrent(org.springframework.security.core.Authentication authentication) {
		return null;
	}

	@Override
	public Client getClientCurrent(Authentication authentication) {
		return clientRepository.findByEmail(authentication.name());
	}
}
