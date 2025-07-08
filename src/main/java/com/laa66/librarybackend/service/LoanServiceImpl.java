package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Loan;
import com.laa66.librarybackend.persistence.LoanPersistence;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanPersistence loanPersistence;
    private final UserService userService;

    @Override
    public List<Loan> findAll() {
        return loanPersistence.findAll();
    }

    @Override
    public Optional<Loan> findByID(long id) {
        return loanPersistence.findByID(id);
    }

    @Override
    public Loan create(long userId, Loan loan) {
        if (loan.getBook()
                .getLoans()
                .stream()
                .anyMatch(l -> l.getActualReturnDate() == null)) {
            throw new RuntimeException();
        }
        if (userService.findByID(userId)
                .orElseThrow(() -> new RuntimeException("user not exists"))
                .getLoans()
                .stream()
                .filter(l -> l.getActualReturnDate() == null)
                .toList()
                .size() >= 5) {
            throw new RuntimeException("too many active loans");
        }
        return loanPersistence.create(loan);
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
