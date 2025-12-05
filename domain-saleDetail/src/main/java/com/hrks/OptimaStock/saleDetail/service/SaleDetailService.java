package com.hrks.OptimaStock.saleDetail.service;

import com.hrks.OptimaStock.saleDetail.model.SaleDetail;
import com.hrks.OptimaStock.saleDetail.repository.SaleDetailRepository;
import com.hrks.OptimaStock.sale.model.Sale;
import com.hrks.OptimaStock.sale.repository.SaleRepository;
import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.product.repository.ProductRepository;
import com.hrks.OptimaStock.iva.model.IVA;
import com.hrks.OptimaStock.iva.repository.IVARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final IVARepository ivaRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository,
                            SaleRepository saleRepository,
                            ProductRepository productRepository,
                            IVARepository ivaRepository) {
        this.saleDetailRepository = saleDetailRepository;
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.ivaRepository = ivaRepository;
    }

    public List<SaleDetail> findAll() {
        return saleDetailRepository.findAll();
    }

    public Optional<SaleDetail> findById(Integer id) {
        return saleDetailRepository.findById(id);
    }

    public SaleDetail save(SaleDetail saleDetail) {
        // Fetch and validate Sale
        if (saleDetail.getSale() != null && saleDetail.getSale().getId() != null) {
            Sale sale = saleRepository.findById(saleDetail.getSale().getId())
                    .orElseThrow(() -> new RuntimeException("Sale with id " + 
                            saleDetail.getSale().getId() + " not found"));
            saleDetail.setSale(sale);
        }

        // Fetch and validate Product
        if (saleDetail.getProduct() != null && saleDetail.getProduct().getId() != null) {
            Product product = productRepository.findById(saleDetail.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product with id " + 
                            saleDetail.getProduct().getId() + " not found"));
            saleDetail.setProduct(product);
        }

        // Fetch and validate IVA
        if (saleDetail.getIva() != null && saleDetail.getIva().getId() != null) {
            IVA iva = ivaRepository.findById(saleDetail.getIva().getId())
                    .orElseThrow(() -> new RuntimeException("IVA with id " + 
                            saleDetail.getIva().getId() + " not found"));
            saleDetail.setIva(iva);
        }

        return saleDetailRepository.save(saleDetail);
    }

    public void delete(Integer id) {
        saleDetailRepository.deleteById(id);
    }
}
