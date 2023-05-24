package com.alfabet.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
public class LoanConfiguration {

    public Integer getNumberOfPayments() {
        return numberOfPayments;
    }

    public Integer getPaymentCycleInDays() {
        return paymentCycleInDays;
    }

    @Value("${loans.numberOfPayments}")
    private Integer numberOfPayments;

    @Value("${loans.paymentCycleInDays}")
    private Integer paymentCycleInDays;


}
