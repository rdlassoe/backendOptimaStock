package com.hrks.OptimaStock.inventoryMovement.repository;

import com.hrks.OptimaStock.inventoryMovement.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Integer> {
}
