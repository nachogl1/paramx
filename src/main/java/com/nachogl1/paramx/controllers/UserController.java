package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.User;
import com.nachogl1.paramx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> get(@PathVariable UUID userId) {
        final User responseBody = service.get(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/users")
    public ResponseEntity<?> add(@RequestBody User user) {
        final User responseBody = service.add(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PutMapping("/users")
    public ResponseEntity<?> update(@RequestBody User user) {
        final User responseBody = service.update(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> delete(@PathVariable UUID parameterId) {
        service.delete(parameterId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
