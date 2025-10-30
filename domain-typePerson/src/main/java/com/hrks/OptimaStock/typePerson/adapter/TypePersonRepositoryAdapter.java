package com.hrks.OptimaStock.typePerson.adapter;

import com.hrks.OptimaStock.common.dto.TypePersonDTO;
import com.hrks.OptimaStock.common.port.TypePersonPort;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TypePersonRepositoryAdapter implements TypePersonPort {

    @Autowired
    private TypePersonRepository repository;

    @Override
    public Optional<TypePersonDTO> findById(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    TypePersonDTO dto = new TypePersonDTO();
                    dto.setIdtypePerson(entity.getIdTypePerson());
                    dto.setTypePerson(entity.getTypePerson());
                    return dto;
                });
    }
}
