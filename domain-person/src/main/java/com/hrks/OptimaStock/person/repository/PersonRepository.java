package com.hrks.OptimaStock.person.repository;

import com.hrks.OptimaStock.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
