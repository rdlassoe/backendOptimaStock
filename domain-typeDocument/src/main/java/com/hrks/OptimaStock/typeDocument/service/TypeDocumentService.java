package com.hrks.OptimaStock.typeDocument.service;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeDocumentService {

    @Autowired
    private TypeDocumentRepository typeDocumentRepository;



    public TypeDocument CreateTypeDocument(TypeDocument typeDocument){
        return typeDocumentRepository.save(typeDocument);
    }

    public List<TypeDocument> GetAllTypeDocument(){

        return typeDocumentRepository.findAll();
    }

    public Optional<TypeDocument> GetById(Long id){
        return typeDocumentRepository.findById(id);
    }

    public TypeDocument update(String typeDocument, TypeDocument newData){
        TypeDocument data = typeDocumentRepository.findBytypeDocument(typeDocument).orElseThrow(() -> new RuntimeException("Tipo Documento No Encontrado" + typeDocument));
        data.settypeDocument(newData.gettypeDocument());
        return typeDocumentRepository.save(data);
    }

    public Optional<TypeDocument> Delete(Long id){
        typeDocumentRepository.deleteById(id);
        return null;
    }



}
