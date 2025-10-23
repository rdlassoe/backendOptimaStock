package com.hrks.OptimaStock.iva.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "iva")
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIva;
    private BigDecimal iva;

    public Iva(){}
    public Iva(BigDecimal iva){
        this.iva = iva;
    }

    public long getIdIva(){
        return idIva;
    }
    public void setIdIva(long idIva) {
        this.idIva = idIva;
    }

    public BigDecimal getIva() {
        return iva;
    }
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }
}
