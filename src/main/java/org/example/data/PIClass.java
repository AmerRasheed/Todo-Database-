package org.example.data;

import org.example.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PIClass {
    Person create(Person person);
    Collection<Person> findAll();
    Optional<Person> findById(int id);
    Collection<Person> findByName(String name);
    int deleteById(int id);
}
