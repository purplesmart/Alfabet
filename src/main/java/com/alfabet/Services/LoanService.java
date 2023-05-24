package com.alfabet.Services;

import com.alfabet.Repositories.ActiveLoanRepository;
import com.alfabet.Repositories.SchedulerRepository;
import com.alfabet.configuration.LoanConfiguration;
import com.alfabet.model.Loan;
import com.alfabet.model.SchedulerLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private ActiveLoanRepository activeLoanRepository;

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private LoanConfiguration loanConfiguration;

    public Long take_loan(Loan loan) {

        loan.setPaid_payments(0);
        Loan saved_loan =  activeLoanRepository.save(loan);
        SchedulerLoan schedulerLoans = new SchedulerLoan(saved_loan.getId(), loanConfiguration.getPaymentCycleInDays());
        schedulerRepository.save(schedulerLoans);

        return saved_loan.getId();
    }
}
