package com.hrks.OptimaStock.typeDocument.controller;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.service.TypeDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-document")
public class TypeDocumentController {

    private final TypeDocumentService typeDocumentService;

    public TypeDocumentController(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    @GetMapping
    public ResponseEntity<List<TypeDocument>> getAll() {
        return ResponseEntity.ok(typeDocumentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDocument> getById(@PathVariable Integer id) {
        return typeDocumentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeDocument> create(@RequestBody TypeDocument typeDocument) {
        return ResponseEntity.ok(typeDocumentService.save(typeDocument));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeDocument> update(@PathVariable Integer id,
                                               @RequestBody TypeDocument typeDocument) {
        return typeDocumentService.findById(id)
                .map(t -> {
                    t.setDescription(typeDocument.getDescription());
                    return ResponseEntity.ok(typeDocumentService.save(t));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        typeDocumentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
