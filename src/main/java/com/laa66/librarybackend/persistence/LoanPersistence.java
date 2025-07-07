package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanPersistence {
    List<Loan> findAll();
    Optional<Loan> findByID(long id);
    Loan create(Loan loan);
    void deleteByID(long id);
    void clearLoans(long id);
}
