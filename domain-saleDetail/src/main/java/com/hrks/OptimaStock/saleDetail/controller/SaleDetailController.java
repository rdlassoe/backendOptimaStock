package com.hrks.OptimaStock.saleDetail.controller;

import com.hrks.OptimaStock.saleDetail.model.SaleDetail;
import com.hrks.OptimaStock.saleDetail.service.SaleDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saleDetails")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    public SaleDetailController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDetail>> getAllSaleDetails() {
        List<SaleDetail> saleDetails = saleDetailService.findAll();
        return ResponseEntity.ok(saleDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetail> getSaleDetailById(@PathVariable Integer id) {
        Optional<SaleDetail> saleDetail = saleDetailService.findById(id);
        return saleDetail.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaleDetail> createSaleDetail(@RequestBody SaleDetail saleDetail) {
        try {
            SaleDetail savedSaleDetail = saleDetailService.save(saleDetail);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSaleDetail);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetail> updateSaleDetail(@PathVariable Integer id, 
                                                       @RequestBody SaleDetail saleDetail) {
        Optional<SaleDetail> existingSaleDetail = saleDetailService.findById(id);
        if (existingSaleDetail.isPresent()) {
            saleDetail.setId(id);
            try {
                SaleDetail updatedSaleDetail = saleDetailService.save(saleDetail);
                return ResponseEntity.ok(updatedSaleDetail);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleDetail(@PathVariable Integer id) {
        Optional<SaleDetail> saleDetail = saleDetailService.findById(id);
        if (saleDetail.isPresent()) {
            saleDetailService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
