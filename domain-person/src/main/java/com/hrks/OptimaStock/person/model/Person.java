package com.hrks.OptimaStock.person.model;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    // RELACIÓN CON type_document
    @ManyToOne
    @JoinColumn(name = "id_type_id", referencedColumnName = "id_type_document")
    private TypeDocument typeDocument;

    @Column(name = "document_number", length = 15)
    private String documentNumber;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "phone", length = 45)
    private String phone;

    // RELACIÓN CON type_person
    @ManyToOne
    @JoinColumn(name = "person_type_id", referencedColumnName = "id_type_person")
    private TypePerson typePerson;

    public Person() {
    }

    public Person(String firstName, String lastName, TypeDocument typeDocument, String documentNumber,
                  String email, String phone, TypePerson typePerson) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.typePerson = typePerson;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePerson typePerson) {
        this.typePerson = typePerson;
    }
}
