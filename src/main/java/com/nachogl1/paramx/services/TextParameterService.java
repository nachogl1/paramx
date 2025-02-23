package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.persistence.TextParameterRepository;
import com.nachogl1.paramx.persistence.ParamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TextParameterService {

    @Autowired
    private TextParameterRepository parameterRepository;
    @Autowired
    private ParamUserRepository paramUserRepository;

    public List<TextParameter> getAllByParamUser(UUID paramUserId) {
        return parameterRepository.findAllByParamUserId(paramUserId);
    }

    public TextParameter save(TextParameter parameter) {
        final UUID userId = parameter.getParamUser().getId();
        final ParamUser user = this.paramUserRepository.findById(userId).orElseThrow();
        parameter.setParamUser(user);

        return parameterRepository.save(parameter);
    }


    public void delete(UUID parameterId) {
        final TextParameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow();
        parameterRepository.delete(parameter);
    }

}
