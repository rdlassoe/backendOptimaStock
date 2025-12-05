package com.hrks.OptimaStock.iva.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "IVA")
@Getter
@Setter
public class IVA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIVA")
    private Integer id;

    @Column(name = "iva", precision = 5, scale = 2)
    private BigDecimal iva;
}
