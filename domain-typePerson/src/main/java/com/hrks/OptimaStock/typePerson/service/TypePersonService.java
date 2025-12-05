package com.hrks.OptimaStock.typePerson.service;

import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePersonService {

    private final TypePersonRepository typePersonRepository;

    public TypePersonService(TypePersonRepository typePersonRepository) {
        this.typePersonRepository = typePersonRepository;
    }

    public List<TypePerson> findAll() {
        return typePersonRepository.findAll();
    }

    public Optional<TypePerson> findById(Integer id) {
        return typePersonRepository.findById(id);
    }

    public TypePerson save(TypePerson typePerson) {
        return typePersonRepository.save(typePerson);
    }

    public void delete(Integer id) {
        typePersonRepository.deleteById(id);
    }
}
