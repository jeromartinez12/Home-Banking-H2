package com.mindhub.homebanking2.Services;
import com.mindhub.homebanking2.Models.Client;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
	Client findClientByEmail(String email);
	void saveClient(Client client);

	Client getClientCurrent(org.springframework.security.core.Authentication authentication);

	Client getClientCurrent(Authentication authentication);
}
