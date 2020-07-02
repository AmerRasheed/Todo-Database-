package org.example.data;

import org.example.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonInterfaceClass {
    Person create(Person person);
    Collection<Person> findAll();
    Optional<Person> findById(int id);
    Collection<Person> findByName(String name);
    boolean deleteById(int id);
}
