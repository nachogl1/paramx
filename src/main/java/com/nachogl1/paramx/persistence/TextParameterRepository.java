package com.nachogl1.paramx.persistence;

import com.nachogl1.paramx.model.TextParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TextParameterRepository {
    List<TextParameter> getAll();

    TextParameter add(TextParameter parameter);

    TextParameter update(TextParameter parameter);

    void delete(UUID parameterId);
}
