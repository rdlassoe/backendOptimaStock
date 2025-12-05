package com.hrks.OptimaStock.product.controller;

import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id,
            @Valid @RequestBody Product product) {
        return productService.findById(id)
                .map(p -> {
                    p.setCode(product.getCode());
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setCategory(product.getCategory());
                    p.setMinQuantity(product.getMinQuantity());
                    p.setCost(product.getCost());
                    p.setPrice(product.getPrice());
                    p.setIva(product.getIva());
                    p.setStatus(product.getStatus());
                    return ResponseEntity.ok(productService.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
