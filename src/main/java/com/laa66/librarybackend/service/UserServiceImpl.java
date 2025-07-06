package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.User;
import com.laa66.librarybackend.persistence.UserPersistence;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistence userPersistence;

    @Override
    public List<User> findAll() {
        return userPersistence.findAll();
    }

    @Override
    public Optional<User> findByID(long id) {
        return userPersistence.findByID(id);
    }

    @Override
    public void deleteByID(long id) {
        userPersistence.deleteByID(id);
    }

    @Override
    public User save(User user) {
        return userPersistence.save(user);
    }
}
