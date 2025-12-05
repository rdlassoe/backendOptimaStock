package com.hrks.OptimaStock.saleDetail.repository;

import com.hrks.OptimaStock.saleDetail.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {
}
