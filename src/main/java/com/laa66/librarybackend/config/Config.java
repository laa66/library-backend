package com.laa66.librarybackend.config;


import com.laa66.librarybackend.persistence.*;
import com.laa66.librarybackend.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserService userService(UserPersistence userPersistence) {
        return new UserServiceImpl(userPersistence);
    }

    @Bean
    public BookService bookService(BookPersistence bookPersistence, CategoryService categoryService) {
        return new BookServiceImpl(bookPersistence, categoryService);
    }

    @Bean
    public CategoryService categoryService(CategoryPersistence categoryPersistence) {
        return new CategoryServiceImpl(categoryPersistence);
    }

    @Bean
    public LoanService loanService(LoanPersistence loanPersistence, UserService userService) {
        return new LoanServiceImpl(loanPersistence, userService);
    }
}

