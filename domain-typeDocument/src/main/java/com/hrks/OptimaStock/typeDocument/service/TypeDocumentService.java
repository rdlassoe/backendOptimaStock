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

    public String CreateTypeDocument(String typeDocument){
        if(typeDocumentRepository.existsByTypeDocument(typeDocument)){
            return "Este tipo Documento (" + typeDocument + ") ya existe";
        }else {
            TypeDocument newTypeDocument = new TypeDocument();
            newTypeDocument.settypeDocument(typeDocument);
            typeDocumentRepository.save(newTypeDocument);
            return "Creado con Exito";
        }
    }

    public List<TypeDocument> GetAllTypeDocument(){
        return typeDocumentRepository.findAll();
    }

    public Optional<TypeDocument> GetById(Long id){
        return typeDocumentRepository.findById(id);
    }

    public TypeDocument update( Long id, TypeDocument newData){
        TypeDocument data = typeDocumentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo Dcumento No encontrado " + id));

        data.settypeDocument(newData.gettypeDocument());
        return typeDocumentRepository.save(data);
    }

    public Optional<TypeDocument> Delete(Long id){
        typeDocumentRepository.deleteById(id);
        return null;
    }

}
