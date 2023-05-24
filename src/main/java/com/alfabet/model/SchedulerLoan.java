package com.alfabet.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class SchedulerLoan {

    public SchedulerLoan() {
    }

    public SchedulerLoan(Long loan_id,Integer paymentCycleInDays) {
        LocalDate futureDate = LocalDate.now().plusDays(paymentCycleInDays);
        schedulerDay = String.format("%d", futureDate.getYear() * 10000 + futureDate.getMonth().getValue() * 100 + futureDate.getDayOfMonth());
        this.loan_id = loan_id;
    }

    public void setScheduler_day(String schedulerDay) {
        this.schedulerDay = schedulerDay;
    }

    private String schedulerDay;

    @Id
    private Long loan_id;

    public Long getId() {
        return loan_id;
    }

    public void setId(Long id) {
        this.loan_id = id;
    }
}
