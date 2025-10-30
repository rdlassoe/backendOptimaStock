package com.hrks.OptimaStock.product.adapter;

import com.hrks.OptimaStock.common.dto.ProductDTO;
import com.hrks.OptimaStock.common.port.out.ProductOutputPort;
import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductAdapter implements ProductOutputPort {

    private final ProductRepository repository;

    public ProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    // --- Entity -> DTO ---
    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getIdProduct());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategoryId(product.getCategoryId());
        dto.setMinimumStock(product.getMinQuantity());

        // Convertir Double → Integer de forma segura
        dto.setCost(BigDecimal.valueOf(product.getCost() != null ? product.getCost().intValue() : null));
        dto.setPrice(BigDecimal.valueOf(product.getPrice() != null ? product.getPrice().intValue() : null));

        dto.setIvaId(product.getTaxId());

        // Convertir Integer (1/0) → Boolean
        dto.setStatus(product.getStatus() != null && product.getStatus() == 1);

        return dto;
    }

    // --- DTO -> Entity ---
    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setIdProduct(dto.getId());
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategoryId(dto.getCategoryId());
        product.setMinQuantity(dto.getMinimumStock());

        // Convertir Integer → Double
        product.setCost(BigDecimal.valueOf(dto.getCost() != null ? dto.getCost().doubleValue() : null));
        product.setPrice(BigDecimal.valueOf(dto.getPrice() != null ? dto.getPrice().doubleValue() : null));

        product.setTaxId(dto.getIvaId());

        // Convertir Boolean → Integer
        product.setStatus(dto.getStatus() != null && dto.getStatus() ? 1 : 0);

        return product;
    }

    // --- Métodos del puerto ---
    @Override
    public ProductDTO save(ProductDTO dto) {
        Product entity = repository.save(toEntity(dto));
        return toDTO(entity);
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return toDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategoryId(dto.getCategoryId());
        product.setMinQuantity(dto.getMinimumStock());
        product.setCost(BigDecimal.valueOf(dto.getCost() != null ? dto.getCost().doubleValue() : null));
        product.setPrice(BigDecimal.valueOf(dto.getPrice() != null ? dto.getPrice().doubleValue() : null));
        product.setTaxId(dto.getIvaId());
        product.setStatus(dto.getStatus() != null && dto.getStatus() ? 1 : 0);

        return toDTO(repository.save(product));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
