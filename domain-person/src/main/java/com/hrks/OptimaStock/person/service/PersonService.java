package com.hrks.OptimaStock.person.service;

import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
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
//con esto rompes la modularidad del sistema revisa el chat que te decia
    @Autowired
    private TypePersonRepository typePersonRepository;

    // CREATE
    public Person createPerson(Person person) {
        // Cargar entidades relacionadas desde la BD (evita TransientObjectException)
        if (person.getTypeDocument() != null && person.getTypeDocument().getIdTypeDocument() != null) {
            TypeDocument typeDocument = typeDocumentRepository.findById(person.getTypeDocument().getIdTypeDocument())
                    .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " +
                            person.getTypeDocument().getIdTypeDocument()));
            person.setTypeDocument(typeDocument);
        }

        if (person.getTypePerson() != null && person.getTypePerson().getIdTypePerson() != null) {
            TypePerson typePerson = typePersonRepository.findById(person.getTypePerson().getIdTypePerson())
                    .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " +
                            person.getTypePerson().getIdTypePerson()));
            person.setTypePerson(typePerson);
        }

        return personRepository.save(person);
    }

    // READ ALL
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // READ BY ID
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    // UPDATE
    public Person updatePerson(Long id, Person personDetails) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            existingPerson.setFirstName(personDetails.getFirstName());
            existingPerson.setLastName(personDetails.getLastName());
            existingPerson.setIdentification(personDetails.getIdentification());
            existingPerson.setEmail(personDetails.getEmail());
            existingPerson.setMobile(personDetails.getMobile());

            // Cargar nuevamente las relaciones desde la BD si vienen en la petición
            if (personDetails.getTypeDocument() != null && personDetails.getTypeDocument().getIdTypeDocument() != null) {
                TypeDocument typeDocument = typeDocumentRepository.findById(personDetails.getTypeDocument().getIdTypeDocument())
                        .orElseThrow(() -> new RuntimeException("TypeDocument not found with ID: " +
                                personDetails.getTypeDocument().getIdTypeDocument()));
                existingPerson.setTypeDocument(typeDocument);
            }

            if (personDetails.getTypePerson() != null && personDetails.getTypePerson().getIdTypePerson() != null) {
                TypePerson typePerson = typePersonRepository.findById(personDetails.getTypePerson().getIdTypePerson())
                        .orElseThrow(() -> new RuntimeException("TypePerson not found with ID: " +
                                personDetails.getTypePerson().getIdTypePerson()));
                existingPerson.setTypePerson(typePerson);
            }

            return personRepository.save(existingPerson);
        } else {
            throw new RuntimeException("Person with ID " + id + " not found");
        }
    }

    // DELETE
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person with ID " + id + " not found");
        }
        personRepository.deleteById(id);
    }
}
