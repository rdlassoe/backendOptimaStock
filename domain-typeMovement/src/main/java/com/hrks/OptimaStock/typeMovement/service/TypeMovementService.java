package com.hrks.OptimaStock.typeMovement.service;

import com.hrks.OptimaStock.typeMovement.model.TypeMovement;
import com.hrks.OptimaStock.typeMovement.repository.TypeMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMovementService {

    private final TypeMovementRepository typeMovementRepository;

    public TypeMovementService(TypeMovementRepository typeMovementRepository) {
        this.typeMovementRepository = typeMovementRepository;
    }

    public List<TypeMovement> findAll() {
        return typeMovementRepository.findAll();
    }

    public Optional<TypeMovement> findById(Integer id) {
        return typeMovementRepository.findById(id);
    }

    public TypeMovement save(TypeMovement typeMovement) {
        return typeMovementRepository.save(typeMovement);
    }

    public void delete(Integer id) {
        typeMovementRepository.deleteById(id);
    }
}
