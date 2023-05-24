package com.alfabet.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    private Long id;

    public Transaction() {
    }

    public Transaction(Long transactionId,Long loan_id, Integer paid_payment_number, Double amountPaid, String transaction_result) {
        this.id = transactionId;
        this.time_stamp = LocalDate.now();
        this.loan_id = loan_id;
        this.paid_payment_number = paid_payment_number;
        this.amountPaid = amountPaid;
        this.transaction_result = transaction_result;
    }

    private LocalDate time_stamp;
    private Long loan_id;
    private Integer paid_payment_number;
    private Double amountPaid;
    private String transaction_result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
