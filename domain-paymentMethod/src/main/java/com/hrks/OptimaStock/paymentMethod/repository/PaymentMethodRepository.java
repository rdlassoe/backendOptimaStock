package com.hrks.OptimaStock.paymentMethod.repository;

import com.hrks.OptimaStock.paymentMethod.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
