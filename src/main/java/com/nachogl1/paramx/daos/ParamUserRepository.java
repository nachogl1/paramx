package com.nachogl1.paramx.daos;

import com.nachogl1.paramx.model.ParamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParamUserRepository extends JpaRepository<ParamUser, UUID> {

    @Query("SELECT new ParamUser(p.id, p.firstName, p.secondName, p.email,null) FROM ParamUser p")
    List<ParamUser> findAllParamUsersWithoutTextParametersList();

    Optional<ParamUser> findByEmail(String email);
}
