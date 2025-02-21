package com.nachogl1.paramx.services;

import com.nachogl1.paramx.model.NumericParameter;
import com.nachogl1.paramx.persistence.NumericParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NumericParameterService {

    @Autowired
    private NumericParameterRepository repository;

    public List<NumericParameter> getAll() {
        return repository.getAll();
    }

    public NumericParameter add(NumericParameter parameter) {
        return repository.add(parameter);
    }

    public NumericParameter update(NumericParameter parameter) {
        return repository.update(parameter);
    }

    public void delete(UUID parameterId) {
        repository.delete(parameterId);
    }

}
