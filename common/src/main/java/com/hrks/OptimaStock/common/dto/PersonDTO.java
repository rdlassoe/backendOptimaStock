package com.hrks.OptimaStock.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    private String mobile;
    private Long typeDocumentId;
    private Long typePersonId;
}
