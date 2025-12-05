package com.hrks.OptimaStock.product.service;

import com.hrks.OptimaStock.category.model.Category;
import com.hrks.OptimaStock.category.repository.CategoryRepository;
import com.hrks.OptimaStock.iva.model.IVA;
import com.hrks.OptimaStock.iva.repository.IVARepository;
import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IVARepository ivaRepository;

    public ProductService(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            IVARepository ivaRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ivaRepository = ivaRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        // Fetch and validate Category
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category with id " +
                            product.getCategory().getId() + " not found"));
            product.setCategory(category);
        }

        // Fetch and validate IVA
        if (product.getIva() != null && product.getIva().getId() != null) {
            IVA iva = ivaRepository.findById(product.getIva().getId())
                    .orElseThrow(() -> new RuntimeException("IVA with id " +
                            product.getIva().getId() + " not found"));
            product.setIva(iva);
        }

        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
