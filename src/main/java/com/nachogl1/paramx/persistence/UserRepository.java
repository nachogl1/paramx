package com.nachogl1.paramx.persistence;

import com.nachogl1.paramx.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository {
    User get(UUID userId);

    User add(User user);

    User update(User user);

    void delete(UUID userId);
}
