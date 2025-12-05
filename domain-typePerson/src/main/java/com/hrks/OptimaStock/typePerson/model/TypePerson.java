package com.hrks.OptimaStock.typePerson.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_person")
public class TypePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_person")
    private Integer idTypePerson;

    @Column(name = "description", length = 15)
    private String description;

    public TypePerson() {
    }

    public TypePerson(String description) {
        this.description = description;
    }

    public Integer getIdTypePerson() {
        return idTypePerson;
    }

    public void setIdTypePerson(Integer idTypePerson) {
        this.idTypePerson = idTypePerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
