package com.mindhub.homebanking2.Repositories;

import com.mindhub.homebanking2.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Set;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Set<Transaction> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
