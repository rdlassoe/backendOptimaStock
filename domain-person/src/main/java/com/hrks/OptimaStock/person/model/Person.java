package com.hrks.OptimaStock.person.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    private String mobile;

    // Solo guardamos los IDs (no relaciones directas)
    private Long typeDocumentId;
    private Long typePersonId;
}
