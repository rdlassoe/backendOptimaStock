package com.hrks.OptimaStock.sale.controller;

import com.hrks.OptimaStock.sale.model.Sale;
import com.hrks.OptimaStock.sale.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable Integer id) {
        return saleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sale> create(@RequestBody Sale sale) {
        return ResponseEntity.ok(saleService.save(sale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable Integer id,
                                       @RequestBody Sale sale) {
        return saleService.findById(id)
                .map(s -> {
                    s.setDateTime(sale.getDateTime());
                    s.setEmployee(sale.getEmployee());
                    s.setClient(sale.getClient());
                    s.setPaymentMethod(sale.getPaymentMethod());
                    s.setTotal(sale.getTotal());
                    return ResponseEntity.ok(saleService.save(s));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
