package com.hrks.OptimaStock.iva.repository;

import com.hrks.OptimaStock.iva.model.IVA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVARepository extends JpaRepository<IVA, Integer> {
}
