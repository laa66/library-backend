package com.laa66.librarybackend.controller;

import com.laa66.librarybackend.dto.PasswordAuthenticateDTO;
import com.laa66.librarybackend.entity.User;
import com.laa66.librarybackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody PasswordAuthenticateDTO passwordAuthenticateDTO,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        Authentication auth = UsernamePasswordAuthenticationToken.unauthenticated(passwordAuthenticateDTO.username(), passwordAuthenticateDTO.password());
        Authentication authRes = authenticationManager.authenticate(auth);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authRes);
        SecurityContextHolder.setContext(securityContext);
        securityContextRepository.saveContext(securityContext, request, response);

        return ResponseEntity.ok("Login success!");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        return ResponseEntity.of(userService.findByID(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.findByID(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName() == null ? user.getFirstName() : updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName() == null ? user.getLastName() : updatedUser.getLastName());
                    user.setPassword(updatedUser.getPassword() == null ? user.getPassword() : updatedUser.getPassword());
                    return ResponseEntity.ok(userService.save(user));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}