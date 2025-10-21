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

    @PostMapping("/create")
    public TypeDocument Create(@RequestBody TypeDocument typeDocument){
        return typeDocumentService.CreateTypeDocument(typeDocument);
    }

    @GetMapping("/list")
    public List<TypeDocument> Get(){
        return typeDocumentService.GetAllTypeDocument();
    }

    @GetMapping("/{id}")
    public Optional<TypeDocument>GetById(@PathVariable Long id){

        return typeDocumentService.GetById(id);
    }

    @PutMapping("/update/{document}")
    public ResponseEntity<TypeDocument> update(@PathVariable String document, @RequestBody TypeDocument typeDocument){
        TypeDocument dataUpdate = typeDocumentService.update(document, typeDocument);
        return ResponseEntity.ok(dataUpdate);
    }

    @DeleteMapping("/{id}")
    public Optional<TypeDocument>DeleteById(@PathVariable Long id){
        return typeDocumentService.Delete(id);
    }

}

