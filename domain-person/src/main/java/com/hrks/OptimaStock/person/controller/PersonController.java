package com.hrks.OptimaStock.person.controller;

import com.hrks.OptimaStock.common.dto.PersonDTO;
import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "*")

public class PersonController {

    @Autowired
    private PersonService personService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) {
        Person createdPerson = personService.createPerson(personDTO);
        return ResponseEntity.ok(createdPerson);
    }


    // ✅ Ver todas las personas
    @GetMapping("/list")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    // ✅ Ver persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    // ✅ Editar persona por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(id, personDTO));
    }

    // ✅ Eliminar persona por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}