package com.hrks.OptimaStock.paymentMethod.controller;

import com.hrks.OptimaStock.paymentMethod.model.PaymentMethod;
import com.hrks.OptimaStock.paymentMethod.service.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAll() {
        return ResponseEntity.ok(paymentMethodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getById(@PathVariable Integer id) {
        return paymentMethodService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod paymentMethod) {
        return ResponseEntity.ok(paymentMethodService.save(paymentMethod));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> update(@PathVariable Integer id,
                                                @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.findById(id)
                .map(pm -> {
                    pm.setDescription(paymentMethod.getDescription());
                    return ResponseEntity.ok(paymentMethodService.save(pm));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        paymentMethodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
