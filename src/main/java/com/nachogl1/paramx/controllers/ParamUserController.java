package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ParamUserController {

    @Autowired
    private UserService service;

    @GetMapping("/users/{paramUserId}")
    public ResponseEntity<ParamUser> get(@PathVariable UUID paramUserId) {
        final ParamUser responseBody = service.get(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ParamUser>> getAll() {
        final List<ParamUser> responseBody = service.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/users")
    public ResponseEntity<ParamUser> add(@RequestBody ParamUser paramUser) {
        final ParamUser responseBody = service.save(paramUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @PutMapping("/users")
    public ResponseEntity<ParamUser> update(@RequestBody ParamUser paramUser) {
        final ParamUser responseBody = service.save(paramUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/users/{paramUserId}")
    public ResponseEntity<Void> delete(@PathVariable UUID paramUserId) {
        service.delete(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
