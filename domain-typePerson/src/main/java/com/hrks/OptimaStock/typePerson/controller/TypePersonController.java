package com.hrks.OptimaStock.typePerson.controller;

import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.service.TypePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typePerson")
public class TypePersonController {

    @Autowired
    private TypePersonService typePersonService;
    // Crear una nueva persona
    @PostMapping ("/create")
    public TypePerson create(@RequestBody TypePerson typePerson) {
        return typePersonService.CreateTypePerson(typePerson);
    }

    // Listar todas las personas
    @GetMapping("/list")
    public List<TypePerson> getAll() {
        return typePersonService.GetAllTypePerson();
    }

    // Obtener una persona por ID
    @GetMapping("list/{id}")
    public Optional<TypePerson> getById(@PathVariable Long id) {
        return typePersonService.GetById(id);
    }

    // Actualizar persona por ID
    @PutMapping("update/{id}")
    public ResponseEntity<TypePerson> update(
            @PathVariable Long id,
            @RequestBody TypePerson typePersonDetails) {
        try {
            TypePerson updated = typePersonService.update(id, typePersonDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar persona por ID
    @DeleteMapping("delete/{id}")
    public Optional<TypePerson> deleteById(@PathVariable Long id) {
        return typePersonService.Delete(id);
    }
}
