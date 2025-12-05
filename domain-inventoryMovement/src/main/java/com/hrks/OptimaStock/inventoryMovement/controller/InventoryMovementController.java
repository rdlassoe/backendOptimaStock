package com.hrks.OptimaStock.inventoryMovement.controller;

import com.hrks.OptimaStock.inventoryMovement.model.InventoryMovement;
import com.hrks.OptimaStock.inventoryMovement.service.InventoryMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @GetMapping
    public List<InventoryMovement> getAllInventoryMovements() {
        return inventoryMovementService.getAllInventoryMovements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovement> getInventoryMovementById(@PathVariable Integer id) {
        return inventoryMovementService.getInventoryMovementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryMovement createInventoryMovement(@Valid @RequestBody InventoryMovement inventoryMovement) {
        return inventoryMovementService.saveInventoryMovement(inventoryMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovement> updateInventoryMovement(@PathVariable Integer id, 
                                                                     @Valid @RequestBody InventoryMovement inventoryMovementDetails) {
        return inventoryMovementService.getInventoryMovementById(id)
                .map(inventoryMovement -> {
                    inventoryMovement.setInventory(inventoryMovementDetails.getInventory());
                    inventoryMovement.setDate(inventoryMovementDetails.getDate());
                    inventoryMovement.setMovementType(inventoryMovementDetails.getMovementType());
                    inventoryMovement.setQuantity(inventoryMovementDetails.getQuantity());
                    inventoryMovement.setDescription(inventoryMovementDetails.getDescription());
                    inventoryMovement.setUser(inventoryMovementDetails.getUser());
                    InventoryMovement updatedInventoryMovement = inventoryMovementService.saveInventoryMovement(inventoryMovement);
                    return ResponseEntity.ok(updatedInventoryMovement);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryMovement(@PathVariable Integer id) {
        return inventoryMovementService.getInventoryMovementById(id)
                .map(inventoryMovement -> {
                    inventoryMovementService.deleteInventoryMovement(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
