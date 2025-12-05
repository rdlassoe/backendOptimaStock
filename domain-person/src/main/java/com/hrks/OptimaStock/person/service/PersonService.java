package com.hrks.OptimaStock.person.service;

import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import com.hrks.OptimaStock.typeDocument.repository.TypeDocumentRepository;
import com.hrks.OptimaStock.typePerson.model.TypePerson;
import com.hrks.OptimaStock.typePerson.repository.TypePersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final TypeDocumentRepository typeDocumentRepository;
    private final TypePersonRepository typePersonRepository;

    public PersonService(PersonRepository personRepository,
            TypeDocumentRepository typeDocumentRepository,
            TypePersonRepository typePersonRepository) {
        this.personRepository = personRepository;
        this.typeDocumentRepository = typeDocumentRepository;
        this.typePersonRepository = typePersonRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        // Fetch and validate TypeDocument
        if (person.getTypeDocument() != null && person.getTypeDocument().getIdTypeDocument() != null) {
            TypeDocument typeDocument = typeDocumentRepository.findById(person.getTypeDocument().getIdTypeDocument())
                    .orElseThrow(() -> new RuntimeException("TypeDocument with id " +
                            person.getTypeDocument().getIdTypeDocument() + " not found"));
            person.setTypeDocument(typeDocument);
        }

        // Fetch and validate TypePerson
        if (person.getTypePerson() != null && person.getTypePerson().getIdTypePerson() != null) {
            TypePerson typePerson = typePersonRepository.findById(person.getTypePerson().getIdTypePerson())
                    .orElseThrow(() -> new RuntimeException("TypePerson with id " +
                            person.getTypePerson().getIdTypePerson() + " not found"));
            person.setTypePerson(typePerson);
        }

        return personRepository.save(person);
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}
