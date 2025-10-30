package com.hrks.OptimaStock.person.service;

import com.hrks.OptimaStock.common.dto.PersonDTO;
import com.hrks.OptimaStock.common.dto.TypeDocumentDTO;
import com.hrks.OptimaStock.common.dto.TypePersonDTO;
import com.hrks.OptimaStock.common.port.TypeDocumentPort;
import com.hrks.OptimaStock.common.port.TypePersonPort;
import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final TypeDocumentPort typeDocumentPort;
    private final TypePersonPort typePersonPort;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         TypeDocumentPort typeDocumentPort,
                         TypePersonPort typePersonPort) {
        this.personRepository = personRepository;
        this.typeDocumentPort = typeDocumentPort;
        this.typePersonPort = typePersonPort;
    }

    // ✅ Crear persona
    @Transactional
    public Person createPerson(PersonDTO dto) {

        // Buscar entidades relacionadas mediante PUERTOS (no repositorios)
        TypeDocumentDTO typeDocument = typeDocumentPort.findById(dto.getTypeDocumentId())
                .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " + dto.getTypeDocumentId()));

        TypePersonDTO typePerson = typePersonPort.findById(dto.getTypePersonId())
                .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " + dto.getTypePersonId()));

        // Crear la entidad Person
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setIdentification(dto.getIdentification());
        person.setEmail(dto.getEmail());
        person.setMobile(dto.getMobile());
        person.setTypeDocumentId(typeDocument.getIdtypeDocument());
        person.setTypePersonId(typePerson.getIdtypePerson());

        return personRepository.save(person);
    }

    // ✅ Obtener todas las personas
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // ✅ Obtener persona por ID
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
    }

    // ✅ Actualizar persona
    @Transactional
    public Person updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));

        // Validar y actualizar las relaciones mediante los puertos
        if (dto.getTypeDocumentId() != null) {
            TypeDocumentDTO typeDocument = typeDocumentPort.findById(dto.getTypeDocumentId())
                    .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " + dto.getTypeDocumentId()));
            person.setTypeDocumentId(typeDocument.getIdtypeDocument());
        }

        if (dto.getTypePersonId() != null) {
            TypePersonDTO typePerson = typePersonPort.findById(dto.getTypePersonId())
                    .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " + dto.getTypePersonId()));
            person.setTypePersonId(typePerson.getIdtypePerson());
        }

        // Actualizar datos básicos
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setIdentification(dto.getIdentification());
        person.setEmail(dto.getEmail());
        person.setMobile(dto.getMobile());

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
