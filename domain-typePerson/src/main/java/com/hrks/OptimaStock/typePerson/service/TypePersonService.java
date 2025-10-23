package com.hrks.OptimaStock.typePerson.service;


import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePersonService {

    @Autowired
    private TypePersonRepository typePersonRepository;

    public TypePerson CreateTypePerson(TypePerson typePerson){
        return typePersonRepository.save(typePerson);
    }

    public List<TypePerson> GetAllTypePerson(){
        return typePersonRepository.findAll();
    }

    public Optional<TypePerson> GetById(Long id){
        return typePersonRepository.findById(id);
    }

    public TypePerson update(Long id, TypePerson typePersonDetails) {
        Optional<TypePerson> optionalTypePerson = typePersonRepository.findById(id);

        if (optionalTypePerson.isPresent()) {
            TypePerson existingTypePerson = optionalTypePerson.get();
            existingTypePerson.settypePerson(typePersonDetails.getTypePerson());
            return typePersonRepository.save(existingTypePerson);
        } else {
            throw new RuntimeException("TypePerson con id " + id + " no encontrado");
        }
    }

    public Optional<TypePerson> Delete(Long id){
        typePersonRepository.deleteById(id);
        return null;
    }

}
