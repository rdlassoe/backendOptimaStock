package com.hrks.OptimaStock.typeDocument.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_document")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypeDocument;
    private  String typeDocument;

    public TypeDocument(){}
    public TypeDocument(String TypeDocument){
        this.typeDocument = typeDocument;
    }

    public Long getidtypeDocument(){
        return idtypeDocument;}
    public void setidtypeDocument(Long id){
        this.idtypeDocument = idtypeDocument;}

    public String gettypeDocument(){
        return typeDocument;}
    public void settypeDocument(String typeDocument){
        this.typeDocument = typeDocument;}

}
