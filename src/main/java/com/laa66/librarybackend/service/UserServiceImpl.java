package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.User;
import com.laa66.librarybackend.persistence.UserPersistence;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistence userPersistence;
    private final PasswordEncoder passwordEncoder;

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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userPersistence.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPersistence.findByEmail(username)
                .map(u -> org.springframework.security.core.userdetails.User.builder()
                        .username(u.getEmail())
                        .password(u.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("user not exists"));
    }
}
