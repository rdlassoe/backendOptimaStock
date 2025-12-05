package com.hrks.OptimaStock.sale.service;

import com.hrks.OptimaStock.paymentMethod.model.PaymentMethod;
import com.hrks.OptimaStock.paymentMethod.repository.PaymentMethodRepository;
import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import com.hrks.OptimaStock.sale.model.Sale;
import com.hrks.OptimaStock.sale.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final PersonRepository personRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public SaleService(SaleRepository saleRepository,
                      PersonRepository personRepository,
                      PaymentMethodRepository paymentMethodRepository) {
        this.saleRepository = saleRepository;
        this.personRepository = personRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Optional<Sale> findById(Integer id) {
        return saleRepository.findById(id);
    }

    public Sale save(Sale sale) {
        // Fetch and validate Employee
        if (sale.getEmployee() != null && sale.getEmployee().getId() != null) {
            Person employee = personRepository.findById(sale.getEmployee().getId())
                    .orElseThrow(() -> new RuntimeException("Employee with id " + 
                            sale.getEmployee().getId() + " not found"));
            sale.setEmployee(employee);
        }

        // Fetch and validate Client
        if (sale.getClient() != null && sale.getClient().getId() != null) {
            Person client = personRepository.findById(sale.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client with id " + 
                            sale.getClient().getId() + " not found"));
            sale.setClient(client);
        }

        // Fetch and validate PaymentMethod
        if (sale.getPaymentMethod() != null && sale.getPaymentMethod().getIdPaymentMethod() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(sale.getPaymentMethod().getIdPaymentMethod())
                    .orElseThrow(() -> new RuntimeException("PaymentMethod with id " + 
                            sale.getPaymentMethod().getIdPaymentMethod() + " not found"));
            sale.setPaymentMethod(paymentMethod);
        }

        return saleRepository.save(sale);
    }

    public void delete(Integer id) {
        saleRepository.deleteById(id);
    }
}
