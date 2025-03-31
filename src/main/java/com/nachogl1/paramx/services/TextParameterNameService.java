package com.nachogl1.paramx.services;

import com.nachogl1.paramx.daos.TextParameterNameRepositoryV2;
import com.nachogl1.paramx.exceptions.ParamxApiException;
import com.nachogl1.paramx.model.ParameterName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TextParameterNameService {

    @Autowired
    private TextParameterNameRepositoryV2 repository;


    public List<ParameterName> getAllTextParameterNamesByParamUserId(UUID paramUserId) {
        return repository.findAllByParamUserId(paramUserId);
    }

    public ParameterName save(ParameterName name) {
        return repository.save(name);
    }

    public void delete(UUID parameterNameId) {
        final ParameterName name = repository.findById(parameterNameId)
                .orElseThrow(() -> new ParamxApiException("Parameter name does not exists", HttpStatus.NOT_FOUND));
        repository.delete(name);
    }

}
