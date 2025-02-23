package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.persistence.ParamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private ParamUserRepository repository;

    public ParamUser get(UUID paramUserId) {
        return repository.findById(paramUserId).orElseThrow();
    }

    public ParamUser save(ParamUser paramUser) {
        return repository.save(paramUser);
    }

    public void delete(UUID paramUserId) {
        final ParamUser paramUser = repository.findById(paramUserId)
                .orElseThrow();
        repository.delete(paramUser);
    }

}
