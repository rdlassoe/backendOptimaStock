package com.hrks.OptimaStock.typeDocument.service;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentService(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    public List<TypeDocument> findAll() {
        return typeDocumentRepository.findAll();
    }

    public Optional<TypeDocument> findById(Integer id) {
        return typeDocumentRepository.findById(id);
    }

    public TypeDocument save(TypeDocument typeDocument) {
        return typeDocumentRepository.save(typeDocument);
    }

    public void delete(Integer id) {
        typeDocumentRepository.deleteById(id);
    }
}
