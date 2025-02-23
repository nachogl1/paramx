package com.nachogl1.paramx.persistence;

import com.nachogl1.paramx.model.ParamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParamUserRepository extends JpaRepository<ParamUser, UUID> {
}
