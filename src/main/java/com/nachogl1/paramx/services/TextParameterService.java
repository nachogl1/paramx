package com.nachogl1.paramx.services;

import com.nachogl1.paramx.exceptions.ParamxApiException;
import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.daos.ParamUserRepository;
import com.nachogl1.paramx.daos.TextParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        final ParamUser user = this.paramUserRepository.findById(userId).orElseThrow(() -> new ParamxApiException("User does not exists", HttpStatus.NOT_FOUND));
        parameter.setParamUser(user);

        return parameterRepository.save(parameter);
    }


    public void delete(UUID parameterId) {
        final TextParameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() -> new ParamxApiException("Parameter does not exists", HttpStatus.NOT_FOUND));
        parameterRepository.delete(parameter);
    }

}
