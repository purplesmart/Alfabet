package com.alfabet.controllers;

import com.alfabet.Services.LoanService;
import com.alfabet.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/take_loan")
    public ResponseEntity<Long> shopping(@RequestBody @Valid Loan loan) {
        try {
            return Optional
                    .ofNullable(loanService.take_loan(loan))
                    .map(loan_id -> ResponseEntity.ok().body(loan_id))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "General error", ex);
        }
    }
}
