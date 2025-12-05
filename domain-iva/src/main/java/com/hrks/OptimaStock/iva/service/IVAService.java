package com.hrks.OptimaStock.iva.service;

import com.hrks.OptimaStock.iva.model.IVA;
import com.hrks.OptimaStock.iva.repository.IVARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IVAService {

    private final IVARepository ivaRepository;

    public IVAService(IVARepository ivaRepository) {
        this.ivaRepository = ivaRepository;
    }

    public List<IVA> findAll() {
        return ivaRepository.findAll();
    }

    public Optional<IVA> findById(Integer id) {
        return ivaRepository.findById(id);
    }

    public IVA save(IVA iva) {
        return ivaRepository.save(iva);
    }

    public void delete(Integer id) {
        ivaRepository.deleteById(id);
    }
}
