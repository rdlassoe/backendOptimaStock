package com.hrks.OptimaStock.typeDocument.adapter;

import com.hrks.OptimaStock.common.dto.TypeDocumentDTO;
import com.hrks.OptimaStock.common.port.TypeDocumentPort;
import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TypeDocumentRepositoryAdapter implements TypeDocumentPort {

    @Autowired
    private TypeDocumentRepository repository;

    @Override
    public Optional<TypeDocumentDTO> findById(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    TypeDocumentDTO dto = new TypeDocumentDTO();
                    dto.setIdtypeDocument(entity.getIdTypeDocument());
                    dto.setTypeDocument(entity.gettypeDocument());
                    return dto;
                });
    }
}
