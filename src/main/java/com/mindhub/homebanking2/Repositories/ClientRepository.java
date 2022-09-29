package com.mindhub.homebanking2.Repositories;
import com.mindhub.homebanking2.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    //FindBy es un metodo, en este caso busca por email y nos trae el objeto cliente que matchea con ese email (completo).
    Client findByEmail(String email);
}

