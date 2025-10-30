package com.hrks.OptimaStock.product.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(name = "id_category")
    private Integer categoryId;

    @Column(name = "min_quantity", nullable = false)
    private Integer minQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "tax_id")
    private Integer taxId;

    @Column(nullable = false)
    private Integer status;
}
