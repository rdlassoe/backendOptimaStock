package com.hrks.OptimaStock.inventory.service;

import com.hrks.OptimaStock.inventory.model.Inventory;
import com.hrks.OptimaStock.inventory.repository.InventoryRepository;
import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findById(Integer id) {
        return inventoryRepository.findById(id);
    }

    public Optional<Inventory> findByProductId(Integer productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public Inventory save(Inventory inventory) {
        // Fetch and validate Product
        if (inventory.getProduct() != null && inventory.getProduct().getId() != null) {
            Product product = productRepository.findById(inventory.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product with id " + 
                            inventory.getProduct().getId() + " not found"));
            inventory.setProduct(product);
        }

        return inventoryRepository.save(inventory);
    }

    public void delete(Integer id) {
        inventoryRepository.deleteById(id);
    }
}
