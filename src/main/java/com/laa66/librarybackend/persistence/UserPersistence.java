package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistence {
    List<User> findAll();
    Optional<User> findByID(long id);
    void deleteByID(long id);
    User save(User user);
    Optional<User> findByEmail(String email);
}
