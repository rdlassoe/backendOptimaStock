package com.hrks.OptimaStock.person.service;

import com.hrks.OptimaStock.common.dto.PersonDTO;
import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    @Autowired
    private TypePersonRepository typePersonRepository;

    @Transactional
    public Person createPerson(PersonDTO dto) {

        // Buscar entidades relacionadas
        TypeDocument typeDocument = typeDocumentRepository.findById(dto.getTypeDocumentId())
                .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " + dto.getTypeDocumentId()));

        TypePerson typePerson = typePersonRepository.findById(dto.getTypePersonId())
                .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " + dto.getTypePersonId()));

        // Crear persona
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setIdentification(dto.getIdentification());
        person.setEmail(dto.getEmail());
        person.setMobile(dto.getMobile());
        person.setTypeDocument(typeDocument);
        person.setTypePerson(typePerson);

        // Guardar persona en la base de datos
        return personRepository.save(person);
    }



    // READ ALL
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // READ BY ID
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
    }

    // UPDATE
    // ✅ Actualizar persona
    @Transactional
    public Person updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));

        // Actualizamos solo los campos enviados
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setIdentification(dto.getIdentification());
        person.setEmail(dto.getEmail());
        person.setMobile(dto.getMobile());

        if (dto.getTypeDocumentId() != null) {
            TypeDocument typeDocument = typeDocumentRepository.findById(dto.getTypeDocumentId())
                    .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " + dto.getTypeDocumentId()));
            person.setTypeDocument(typeDocument);
        }

        if (dto.getTypePersonId() != null) {
            TypePerson typePerson = typePersonRepository.findById(dto.getTypePersonId())
                    .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " + dto.getTypePersonId()));
            person.setTypePerson(typePerson);
        }

        return personRepository.save(person);
    }

    // ✅ Eliminar persona
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with ID: " + id);
        }
        personRepository.deleteById(id);
    }
}
