package com.nachogl1.paramx.daos;

import com.nachogl1.paramx.model.TextParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TextParameterRepository extends JpaRepository<TextParameter, UUID> {

    List<TextParameter> findAllByParamUserId(UUID paramUserId);

}
