package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Book;
import com.laa66.librarybackend.entity.Loan;
import com.laa66.librarybackend.entity.User;
import com.laa66.librarybackend.persistence.LoanPersistence;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanPersistence loanPersistence;
    private final UserService userService;
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
    public List<Loan> findByUserID(long userId) {
        return loanPersistence.findByUserID(userId);
    }

    @Override
    public Loan create(long userId, long bookId) {
        Book book = bookService.findByID(bookId).orElseThrow(() -> new RuntimeException("book not exists"));
        if (book.getLoans()
                .stream()
                .anyMatch(l -> l.getActualReturnDate() == null)) {
            throw new RuntimeException();
        }
        User user = userService.findByID(userId).orElseThrow(() -> new RuntimeException("user not exists"));
        if (user.getLoans()
                .stream()
                .filter(l -> l.getActualReturnDate() == null)
                .toList()
                .size() >= 5) {
            throw new RuntimeException("too many active loans");
        }
        LocalDate now = LocalDate.now();
        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .startDate(now)
                .expReturnDate(now.plusMonths(1))
                .build();
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
