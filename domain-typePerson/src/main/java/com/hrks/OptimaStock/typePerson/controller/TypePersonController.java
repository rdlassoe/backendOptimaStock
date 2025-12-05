package com.hrks.OptimaStock.typePerson.controller;

import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.service.TypePersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-person")
public class TypePersonController {

    private final TypePersonService typePersonService;

    public TypePersonController(TypePersonService typePersonService) {
        this.typePersonService = typePersonService;
    }

    @GetMapping
    public ResponseEntity<List<TypePerson>> getAll() {
        return ResponseEntity.ok(typePersonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePerson> getById(@PathVariable Integer id) {
        return typePersonService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypePerson> create(@RequestBody TypePerson typePerson) {
        return ResponseEntity.ok(typePersonService.save(typePerson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypePerson> update(@PathVariable Integer id,
                                             @RequestBody TypePerson typePerson) {
        return typePersonService.findById(id)
                .map(t -> {
                    t.setDescription(typePerson.getDescription());
                    return ResponseEntity.ok(typePersonService.save(t));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        typePersonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
