package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByID(long id);
    void deleteByID(long id);
    User save(User user);
}
