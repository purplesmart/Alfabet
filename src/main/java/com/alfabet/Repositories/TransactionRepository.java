package com.alfabet.Repositories;

import com.alfabet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

}
