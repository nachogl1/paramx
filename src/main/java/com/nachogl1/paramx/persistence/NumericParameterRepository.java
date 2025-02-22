package com.nachogl1.paramx.persistence;

import com.nachogl1.paramx.model.NumericParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NumericParameterRepository extends JpaRepository<NumericParameter, UUID> {

}
