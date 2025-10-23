package com.hrks.OptimaStock.iva.controller;

import com.hrks.OptimaStock.iva.model.Iva;
import com.hrks.OptimaStock.iva.service.IvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/iva")
public class IvaController {

    @Autowired
    private IvaService ivaService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody Iva iva){
        String answer = ivaService.createIva(iva.getIva());
        return ResponseEntity.ok(answer);
    }

    @GetMapping
    public List<Iva> GetAll(){
        return ivaService.GetAllIva();
    }

    @GetMapping("/{id}")
    public Optional<Iva> GetById(@PathVariable Long id){
        return ivaService.GetById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Iva> Update(@PathVariable Long id, @RequestBody Iva iva){
        Iva dataUpdate = ivaService.Update(id, iva);
        return ResponseEntity.ok(dataUpdate);
    }

    @DeleteMapping("/{id}")
    public Optional<Iva> Delete(@PathVariable Long id){
        return ivaService.Delete(id);
    }
}
