package com.hrks.OptimaStock.paymentMethod.service;

import com.hrks.OptimaStock.paymentMethod.model.PaymentMethod;
import com.hrks.OptimaStock.paymentMethod.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public Optional<PaymentMethod> findById(Integer id) {
        return paymentMethodRepository.findById(id);
    }

    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public void delete(Integer id) {
        paymentMethodRepository.deleteById(id);
    }
}
