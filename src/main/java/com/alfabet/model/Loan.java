package com.alfabet.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getSrc_account() {
        return src_account;
    }

    public String getDst_account() {
        return dst_account;
    }

    public Double getAmount() {
        return amount;
    }

    @NotEmpty(message = "src account can't be empty.")
    private String src_account;

    @NotEmpty(message = "dst account can't be empty.")
    private String dst_account;

    @NotEmpty(message = "amount can't be empty.")
    private Double amount;

    public Integer getPaid_payments() {
        return paid_payments;
    }

    public void setPaid_payments(Integer paid_payments) {
        this.paid_payments = paid_payments;
    }

    private Integer paid_payments;

    public Loan() {
    }

    public Loan(String src_account, String dst_account, Double amount ){
        this.src_account = src_account;
        this.dst_account = dst_account;
        this.amount = amount;
        paid_payments = 0;
    }

    public void payment_paid(){
        paid_payments += 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
