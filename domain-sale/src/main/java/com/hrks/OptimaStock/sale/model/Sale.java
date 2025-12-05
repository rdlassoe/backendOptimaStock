package com.hrks.OptimaStock.sale.model;

import com.hrks.OptimaStock.paymentMethod.model.PaymentMethod;
import com.hrks.OptimaStock.person.model.Person;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Person employee;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Person client;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id_payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "total")
    private Integer total;

    public Sale() {
    }

    public Sale(LocalDateTime dateTime, Person employee, Person client, PaymentMethod paymentMethod, Integer total) {
        this.dateTime = dateTime;
        this.employee = employee;
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
