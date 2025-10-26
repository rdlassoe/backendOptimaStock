package com.hrks.OptimaStock.person.model;


import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private Long idPerson;

    @Column(name = "firstName", length = 45)
    private String firstName;

    @Column(name = "lastName", length = 45)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeDocumentId", referencedColumnName = "idTypeDocument")
    private TypeDocument typeDocument;

    @Column(name = "identification", length = 15)
    private String identification;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "mobile", length = 45)
    private String mobile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typePersonId", referencedColumnName = "idTypePerson")
    private TypePerson typePerson;

    public Person() {}

    public Person(String firstName, String lastName, TypeDocument typeDocument, String identification,
                  String email, String mobile, TypePerson typePerson) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.identification = identification;
        this.email = email;
        this.mobile = mobile;
        this.typePerson = typePerson;
    }


    // Getters and setters
    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public TypePerson getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePerson typePerson) {
        this.typePerson = typePerson;
    }
}
