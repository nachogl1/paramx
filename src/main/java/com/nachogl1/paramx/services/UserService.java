package com.nachogl1.paramx.services;

import com.nachogl1.paramx.daos.ParamUserRepository;
import com.nachogl1.paramx.exceptions.ParamxApiException;
import com.nachogl1.paramx.model.ParamUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private ParamUserRepository repository;

    public ParamUser get(UUID paramUserId) {
        return repository.findById(paramUserId)
                .orElseThrow(() -> new ParamxApiException("User does not exists", HttpStatus.NOT_FOUND));
    }

    public ParamUser save(ParamUser paramUser) {
        repository.findByEmail(paramUser.getEmail()).ifPresent(user -> {
            throw new ParamxApiException("User already exists", HttpStatus.CONFLICT);
        });

        return repository.save(paramUser);
    }

    public void delete(UUID paramUserId) {
        final ParamUser paramUser = this.get(paramUserId);
        repository.delete(paramUser);
    }

    public List<ParamUser> getAll() {
        return this.repository.findAllParamUsersWithoutTextParametersList();
    }
}
