package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.persistence.TextParameterRepository;
import com.nachogl1.paramx.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TextParameterService {

    @Autowired
    private TextParameterRepository parameterRepository;
    @Autowired
    private UserRepository userRepository;

    public List<TextParameter> getAllByUser(UUID userId) {
        return parameterRepository.findAllByParamUserId(userId);
    }

    public TextParameter save(TextParameter parameter) {
        final UUID userId = parameter.getParamUser().getId();
        final ParamUser user = this.userRepository.findById(userId).orElseThrow();
        parameter.setParamUser(user);

        return parameterRepository.save(parameter);
    }


    public void delete(UUID parameterId) {
        final TextParameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow();
        parameterRepository.delete(parameter);
    }

}
