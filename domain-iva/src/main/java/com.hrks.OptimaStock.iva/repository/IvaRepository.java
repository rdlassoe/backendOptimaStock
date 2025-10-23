package com.hrks.OptimaStock.iva.repository;

import com.hrks.OptimaStock.iva.model.Iva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IvaRepository extends JpaRepository<Iva, Long> {
    Boolean existsByIva(BigDecimal iva);
}
