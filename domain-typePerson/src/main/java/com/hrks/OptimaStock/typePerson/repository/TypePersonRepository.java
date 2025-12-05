package com.hrks.OptimaStock.typePerson.repository;

import com.hrks.OptimaStock.typePerson.model.TypePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePersonRepository extends JpaRepository<TypePerson, Integer> {

}
