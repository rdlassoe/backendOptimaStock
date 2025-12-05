package com.hrks.OptimaStock.iva.controller;

import com.hrks.OptimaStock.iva.model.IVA;
import com.hrks.OptimaStock.iva.service.IVAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iva")
public class IVAController {

    private final IVAService ivaService;

    public IVAController(IVAService ivaService) {
        this.ivaService = ivaService;
    }

    @GetMapping
    public ResponseEntity<List<IVA>> getAll() {
        return ResponseEntity.ok(ivaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IVA> getById(@PathVariable Integer id) {
        return ivaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IVA> create(@RequestBody IVA iva) {
        return ResponseEntity.ok(ivaService.save(iva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IVA> update(@PathVariable Integer id,
            @RequestBody IVA iva) {
        return ivaService.findById(id)
                .map(i -> {
                    i.setIva(iva.getIva());
                    return ResponseEntity.ok(ivaService.save(i));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ivaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
