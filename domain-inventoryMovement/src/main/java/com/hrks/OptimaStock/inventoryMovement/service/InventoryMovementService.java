package com.hrks.OptimaStock.inventoryMovement.service;

import com.hrks.OptimaStock.inventoryMovement.model.InventoryMovement;
import com.hrks.OptimaStock.inventoryMovement.repository.InventoryMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    public List<InventoryMovement> getAllInventoryMovements() {
        return inventoryMovementRepository.findAll();
    }

    public Optional<InventoryMovement> getInventoryMovementById(Integer id) {
        return inventoryMovementRepository.findById(id);
    }

    public InventoryMovement saveInventoryMovement(InventoryMovement inventoryMovement) {
        return inventoryMovementRepository.save(inventoryMovement);
    }

    public void deleteInventoryMovement(Integer id) {
        inventoryMovementRepository.deleteById(id);
    }
}
