package com.nachogl1.paramx.daos;

import com.nachogl1.paramx.model.ParameterName;
import com.nachogl1.paramx.model.TextParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TextParameterNameRepositoryV2 extends JpaRepository<ParameterName, UUID> {
    List<ParameterName> findAllByParamUserId(UUID paramUserId);
}
