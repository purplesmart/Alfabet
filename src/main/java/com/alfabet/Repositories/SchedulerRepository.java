package com.alfabet.Repositories;

import com.alfabet.model.SchedulerLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface SchedulerRepository extends JpaRepository<SchedulerLoan, Long> {
    List<SchedulerLoan> findBySchedulerDay(String scheduler_day);
}
