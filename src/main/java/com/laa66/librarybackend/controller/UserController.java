package com.laa66.librarybackend.controller;

import com.laa66.librarybackend.entity.User;
import com.laa66.librarybackend.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserPersistence userPersistence;

    // GET /api/users
    @GetMapping
    public void getAllUsers() {
        List<User> allUsers = userPersistence.findAllUsers();
        System.out.println(allUsers);
    }

    // GET /api/users/{id}
//    @GetMapping("/{id}")
//    public void getUserById(@PathVariable Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    // POST /api/users
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }

    // PUT /api/users/{id}
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setFirstName(updatedUser.getFirstName());
//                    user.setLastName(updatedUser.getLastName());
//                    user.setLogin(updatedUser.getLogin());
//                    user.setEmail(updatedUser.getEmail());
//                    user.setBirthDate(updatedUser.getBirthDate());
//                    user.setPassword(updatedUser.getPassword());
//                    return ResponseEntity.ok(userRepository.save(user));
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }
}