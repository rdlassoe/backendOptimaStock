package com.hrks.OptimaStock.product.controller;

import com.hrks.OptimaStock.common.dto.ProductDTO;
import com.hrks.OptimaStock.common.port.in.ProductInputPort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductInputPort productInputPort;

    public ProductController(ProductInputPort productInputPort) {
        this.productInputPort = productInputPort;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productInputPort.createProduct(productDTO);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productInputPort.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productInputPort.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productInputPort.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productInputPort.deleteProduct(id);
    }
}
