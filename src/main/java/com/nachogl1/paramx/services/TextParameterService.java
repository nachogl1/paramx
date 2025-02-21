package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.persistence.TextParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TextParameterService {

    @Autowired
    private TextParameterRepository repository;

    public List<TextParameter> getAll() {
        return repository.getAll();
    }

    public TextParameter add(TextParameter parameter) {
        return repository.add(parameter);
    }

    public TextParameter update(TextParameter parameter) {
        return repository.update(parameter);
    }

    public void delete(UUID parameterId) {
        repository.delete(parameterId);
    }

}
