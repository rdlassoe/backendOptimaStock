package com.hrks.OptimaStock.common.port.out;

import com.hrks.OptimaStock.common.dto.ProductDTO;
import java.util.List;

public interface ProductOutputPort {

    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    ProductDTO update(Long id, ProductDTO productDTO);

    void delete(Long id);
}
