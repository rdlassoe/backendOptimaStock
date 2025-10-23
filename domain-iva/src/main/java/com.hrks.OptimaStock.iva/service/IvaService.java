package com.hrks.OptimaStock.iva.service;

import com.hrks.OptimaStock.iva.model.Iva;
import com.hrks.OptimaStock.iva.repository.IvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IvaService {
    @Autowired
    private IvaRepository ivaRepository;

    public String createIva(BigDecimal iva){
        if(ivaRepository.existsByIva(iva)){
            return "Este registro (" + iva + ") ya existe";
        }else{
            Iva newIva = new Iva();
            newIva.setIva(iva);
            ivaRepository.save(newIva);
            return "Creado con Exito";
        }
    }

    public List<Iva> GetAllIva(){
        return ivaRepository.findAll();
    }

    public Optional<Iva> GetById(Long id){
        return ivaRepository.findById(id);
    }

    public Iva Update(Long id, Iva newData){
        Iva data = ivaRepository.findById(id).orElseThrow(() -> new RuntimeException("Iva no encontrado " + id));

        data.setIva(newData.getIva());
        return ivaRepository.save(data);

    }

    public Optional<Iva> Delete(Long id){
        ivaRepository.deleteById(id);
        return null;
    }
}
