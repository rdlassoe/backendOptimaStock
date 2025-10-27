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
            return "Este registro (" + typeDocument + ") ya existe";
        }else{
            TypeDocument newtypeDocument = new TypeDocument();
            newtypeDocument.settypeDocument(typeDocument);
            typeDocumentRepository.save(newtypeDocument);
            return "Creado con exito";
        }
    }

    public List<TypeDocument> GetAllTypeDocument(){

        return typeDocumentRepository.findAll();
    }

    public Optional<TypeDocument> GetById(Long id){
        return typeDocumentRepository.findById(id);
    }

    public TypeDocument update(Long id, TypeDocument newData){
        TypeDocument data = typeDocumentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo Documento No Encontrado" + id));
        data.settypeDocument(newData.gettypeDocument());
        return typeDocumentRepository.save(data);
    }

    public Optional<TypeDocument> Delete(Long id){
        typeDocumentRepository.deleteById(id);
        return null;
    }



}
