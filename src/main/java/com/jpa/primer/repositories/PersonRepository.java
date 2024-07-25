package com.jpa.primer.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jpa.primer.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long> {
    Optional<Person>findByNameContaining(String name);

    @Query("select p from Person p where p.name=?1")
    Optional<Person>EncuentraNombre(String name);

    List<Person> findByLastnameStartingWith(String lastname);

    @Query("select p from Person p where p.lastname=?1")
    List<Person> encuentraApellido(String lastname);
}
