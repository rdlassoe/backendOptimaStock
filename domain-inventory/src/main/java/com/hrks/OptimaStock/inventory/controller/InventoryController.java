package com.hrks.OptimaStock.inventory.controller;

import com.hrks.OptimaStock.inventory.model.Inventory;
import com.hrks.OptimaStock.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryList = inventoryService.findAll();
        return ResponseEntity.ok(inventoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer id) {
        Optional<Inventory> inventory = inventoryService.findById(id);
        return inventory.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Integer productId) {
        Optional<Inventory> inventory = inventoryService.findByProductId(productId);
        return inventory.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        try {
            Inventory savedInventory = inventoryService.save(inventory);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id,
            @Valid @RequestBody Inventory inventory) {
        Optional<Inventory> existingInventory = inventoryService.findById(id);
        if (existingInventory.isPresent()) {
            inventory.setId(id);
            try {
                Inventory updatedInventory = inventoryService.save(inventory);
                return ResponseEntity.ok(updatedInventory);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        Optional<Inventory> inventory = inventoryService.findById(id);
        if (inventory.isPresent()) {
            inventoryService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
