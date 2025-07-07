package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Loan;
import com.laa66.librarybackend.persistence.BookPersistence;
import com.laa66.librarybackend.persistence.LoanPersistence;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanPersistence loanPersistence;
    private final BookService bookService;

    @Override
    public List<Loan> findAll() {
        return loanPersistence.findAll();
    }

    @Override
    public Optional<Loan> findByID(long id) {
        return loanPersistence.findByID(id);
    }

    @Override
    public Loan create(Loan loan) {
        // check if book is loaned


        // check if user has < 5 loans

        // create loan

    }

    @Override
    public void deleteByID(long id) {
        loanPersistence.deleteByID(id);
    }

    @Override
    public void clearLoans(long userId) {
        loanPersistence.clearLoans(userId);
    }
}
