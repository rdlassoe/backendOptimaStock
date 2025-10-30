package com.hrks.OptimaStock.common.dto;

import lombok.Data;

@Data
public class PersonResponseDTO {
    private Long idPerson;
    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    private String mobile;
    private TypeDocumentDTO typeDocument;
    private TypePersonDTO typePerson;
}
