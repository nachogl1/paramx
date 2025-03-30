package com.nachogl1.paramx.services;

import com.nachogl1.paramx.daos.ParamUserRepository;
import com.nachogl1.paramx.daos.TextParameterNameRepository;
import com.nachogl1.paramx.daos.TextParameterRepository;
import com.nachogl1.paramx.exceptions.ParamxApiException;
import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.model.TextParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TextParameterNameService {

    @Autowired
    private TextParameterNameRepository repository;


    public List<String> getAllTextParameterNamesByParamUserId(UUID paramUserId) {
        return repository.findDistinctNamesByUserId(paramUserId);
    }


}
