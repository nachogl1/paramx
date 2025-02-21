package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.User;
import com.nachogl1.paramx.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User get(UUID userId) {
        return repository.get(userId);
    }

    public User add(User user) {
        return repository.add(user);
    }

    public User update(User user) {
        return repository.update(user);
    }

    public void delete(UUID userId) {
        repository.delete(userId);
    }

}
