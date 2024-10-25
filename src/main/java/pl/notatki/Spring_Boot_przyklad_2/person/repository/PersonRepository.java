package pl.notatki.Spring_Boot_przyklad_2.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.notatki.Spring_Boot_przyklad_2.person.model.Person;


public interface PersonRepository extends JpaRepository<Person, Integer> {}
