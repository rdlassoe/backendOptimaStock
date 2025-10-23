package com.hrks.OptimaStock.typePerson.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_Person")
public class TypePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypePerson;
    private String typePerson;

    //getIdTypePerson

    public TypePerson() {}

    public TypePerson(String typePerson) {
        this.typePerson = typePerson;
    }

    public Long getIdTypePerson() { return idtypePerson; }
    public void setIdtypePerson(Long id) { this.idtypePerson = id; }

    public String getTypePerson() { return typePerson; }
    public void settypePerson(String typePerson) { this.typePerson = typePerson; }


}