package com.hrks.OptimaStock.typeMovement.controller;

import com.hrks.OptimaStock.typeMovement.model.TypeMovement;
import com.hrks.OptimaStock.typeMovement.service.TypeMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-movement")
public class TypeMovementController {

    private final TypeMovementService typeMovementService;

    public TypeMovementController(TypeMovementService typeMovementService) {
        this.typeMovementService = typeMovementService;
    }

    @GetMapping
    public ResponseEntity<List<TypeMovement>> getAll() {
        return ResponseEntity.ok(typeMovementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeMovement> getById(@PathVariable Integer id) {
        return typeMovementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeMovement> create(@RequestBody TypeMovement typeMovement) {
        return ResponseEntity.ok(typeMovementService.save(typeMovement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeMovement> update(@PathVariable Integer id,
                                               @RequestBody TypeMovement typeMovement) {
        return typeMovementService.findById(id)
                .map(tm -> {
                    tm.setDescription(typeMovement.getDescription());
                    return ResponseEntity.ok(typeMovementService.save(tm));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        typeMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
