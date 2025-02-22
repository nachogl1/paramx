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
        return repository.findAll();
    }

    public NumericParameter save(NumericParameter parameter) {
        return repository.save(parameter);
    }

    public void delete(UUID parameterId) {
        final NumericParameter parameter = repository.findById(parameterId)
                .orElseThrow();
        repository.delete(parameter);
    }

}
