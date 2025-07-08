package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    List<Loan> findAll();
    Optional<Loan> findByID(long id);
    List<Loan> findByUserID(long userId);
    Loan create(long userId, long bookId);
    void deleteByID(long id);
    void clearLoans(long id);
}
