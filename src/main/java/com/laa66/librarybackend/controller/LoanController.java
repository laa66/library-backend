package com.laa66.librarybackend.controller;

import com.laa66.librarybackend.entity.Loan;
import com.laa66.librarybackend.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/loan")
@RestController
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanByID(@PathVariable long id) {
        return ResponseEntity.of(loanService.findByID(id));
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanService.create(loan);
        return ResponseEntity.ok(createdLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long id) {
        loanService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearLoans(@PathVariable long userId) {
        loanService.clearLoans(userId);
        return ResponseEntity.noContent().build();
    }

}
