package com.nachogl1.paramx.daos;

import com.nachogl1.paramx.model.TextParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TextParameterNameRepository extends JpaRepository<TextParameter, UUID> {

    @Query("SELECT DISTINCT tp.name FROM TextParameter tp WHERE tp.paramUser.id = :paramUserId")
    List<String> findDistinctNamesByUserId(UUID paramUserId);

}
