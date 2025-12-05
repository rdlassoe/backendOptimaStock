package com.hrks.OptimaStock.typeMovement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_movement")
public class TypeMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtype_movement")
    private Integer idTypeMovement;

    @Column(name = "description", length = 100)
    private String description;

    public TypeMovement() {
    }

    public TypeMovement(String description) {
        this.description = description;
    }

    public Integer getIdTypeMovement() {
        return idTypeMovement;
    }

    public void setIdTypeMovement(Integer idTypeMovement) {
        this.idTypeMovement = idTypeMovement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
