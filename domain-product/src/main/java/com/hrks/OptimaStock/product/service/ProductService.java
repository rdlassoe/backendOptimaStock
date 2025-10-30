package com.hrks.OptimaStock.product.service;

import com.hrks.OptimaStock.common.dto.ProductDTO;
import com.hrks.OptimaStock.common.port.in.ProductInputPort;
import com.hrks.OptimaStock.common.port.out.ProductOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService implements ProductInputPort {

    private final ProductOutputPort productOutputPort;

    public ProductService(ProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Aquí no se toca el modelo Product, solo se pasa el DTO al puerto de salida
        return productOutputPort.save(productDTO);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productOutputPort.findAll();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productOutputPort.findById(id);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return productOutputPort.update(id, productDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        productOutputPort.delete(id);
    }
}
