package com.alfabet.Repositories;

import com.alfabet.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ActiveLoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByidIn(List<Long> ids);
}
