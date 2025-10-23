package com.hrks.OptimaStock.typeDocument.controller;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.service.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeDocuments")

public class TypeDocumentController {

    @Autowired
    private TypeDocumentService typeDocumentService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody TypeDocument typeDocument){
        String answer = typeDocumentService.CreateTypeDocument(typeDocument.gettypeDocument());
        return ResponseEntity.ok(answer);
    }

    @GetMapping
    public List<TypeDocument> GetAll(){
        return typeDocumentService.GetAllTypeDocument();
    }

    @GetMapping("/{id}")
    public Optional<TypeDocument>GetById(@PathVariable Long id){

        return typeDocumentService.GetById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeDocument> update(@PathVariable Long id, @RequestBody TypeDocument typeDocument){
        TypeDocument dataUpdate = typeDocumentService.update(id, typeDocument);
        return ResponseEntity.ok(dataUpdate);
    }

    @DeleteMapping("/{id}")
    public Optional<TypeDocument>DeleteById(@PathVariable Long id){
        return typeDocumentService.Delete(id);
    }

}

