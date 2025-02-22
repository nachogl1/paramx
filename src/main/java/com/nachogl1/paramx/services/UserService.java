package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ParamUser get(UUID userId) {
        return repository.findById(userId).orElseThrow();
    }

    public ParamUser save(ParamUser user) {
        return repository.save(user);
    }

    public void delete(UUID userId) {
        final ParamUser user = repository.findById(userId)
                .orElseThrow();
        repository.delete(user);
    }

}
