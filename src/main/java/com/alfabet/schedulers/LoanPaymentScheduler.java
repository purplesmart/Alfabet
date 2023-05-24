package com.alfabet.schedulers;

import com.alfabet.Repositories.*;
import com.alfabet.configuration.LoanConfiguration;
import com.alfabet.externals.Processor;
import com.alfabet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanPaymentScheduler {

    @Autowired
    private ActiveLoanRepository activeLoanRepository;

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Processor processor;

    @Autowired
    private LoanConfiguration loanConfiguration;

    @Scheduled(fixedRate = 5000)//(cron = "0 0 0 * * *")
    public void loan_payment_task() {

        List<SchedulerLoan> schedulerLoans = getSchedulerLoans();

        if(schedulerLoans.size() > 0) {

            List<Long> loansId = schedulerLoans.stream().map(SchedulerLoan::getId).collect(Collectors.toList());
            List<Loan> loans = activeLoanRepository.findByidIn(loansId);

            for (Loan loan : loans) {
                SchedulerLoan schedulerLoan =
                        schedulerLoans.stream().filter(sl -> sl.getId().equals(loan.getId())).findFirst().get();
                try {
                    make_payment(loan);
                    if (loan.getPaid_payments() < loanConfiguration.getNumberOfPayments()) {
                        reschedule_loan(schedulerLoan);
                        activeLoanRepository.save(loan);
                    } else {
                        loan_finished(schedulerLoan, loan);
                    }
                } catch (Exception e) {
                    reschedule_loan(schedulerLoan);
                    Transaction transaction = new Transaction((long) -1, loan.getId(), loan.getPaid_payments(), 0.0, "Failed");
                    transactionRepository.save(transaction);
                }
            }
        }
    }

    private List<SchedulerLoan> getSchedulerLoans(){
        LocalDate currentDay = LocalDate.now();
        String today = String.format("%d", currentDay.getYear() * 10000 + currentDay.getMonth().getValue() * 100 + currentDay.getDayOfMonth());
        return schedulerRepository.findBySchedulerDay(today);
    }

    private void make_payment(Loan loan){

        Double paymentAmount = loan.getAmount() / loanConfiguration.getNumberOfPayments();

        Long transactionId =
                processor.make_transaction(loan.getSrc_account(), loan.getDst_account(), paymentAmount);

        Transaction transaction = new Transaction(transactionId, loan.getId(), loan.getPaid_payments(),paymentAmount, "Success");
        transactionRepository.save(transaction);

        loan.payment_paid();
    }

    private void reschedule_loan(SchedulerLoan schedulerLoan){
        LocalDate futureDate = LocalDate.now().plusDays(loanConfiguration.getPaymentCycleInDays());
        schedulerLoan.setScheduler_day(String.format("%d", futureDate.getYear() * 10000 + futureDate.getMonth().getValue() * 100 + futureDate.getDayOfMonth()));
        schedulerRepository.save(schedulerLoan);
    }

    private void loan_finished(SchedulerLoan schedulerLoan, Loan loan){
        schedulerRepository.delete(schedulerLoan);
        activeLoanRepository.delete(loan);
    }
}
