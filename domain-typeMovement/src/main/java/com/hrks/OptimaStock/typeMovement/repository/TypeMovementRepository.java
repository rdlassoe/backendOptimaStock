package com.hrks.OptimaStock.typeMovement.repository;

import com.hrks.OptimaStock.typeMovement.model.TypeMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMovementRepository extends JpaRepository<TypeMovement, Integer> {
}
