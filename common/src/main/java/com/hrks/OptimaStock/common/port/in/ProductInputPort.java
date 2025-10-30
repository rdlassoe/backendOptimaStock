package com.hrks.OptimaStock.common.port.in;

import com.hrks.OptimaStock.common.dto.ProductDTO;
import java.util.List;

public interface ProductInputPort {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
