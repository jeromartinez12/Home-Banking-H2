package com.mindhub.homebanking2.Repositories;
import com.mindhub.homebanking2.Models.Loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository  extends JpaRepository<Loan, Long>{
	Loan findById(long id);
	Loan findByName(String name);
}
