package com.hrks.OptimaStock.typeDocument.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_document")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_document")
    private Integer idTypeDocument;

    @Column(name = "description", length = 15)
    private String description;

    public TypeDocument() {
    }

    public TypeDocument(String description) {
        this.description = description;
    }

    public Integer getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(Integer idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
