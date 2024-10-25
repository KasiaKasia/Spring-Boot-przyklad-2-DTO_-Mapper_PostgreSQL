package pl.notatki.Spring_Boot_przyklad_2.person.service;

import pl.notatki.Spring_Boot_przyklad_2.person.dto.PersonDTO;
import java.util.List;

public interface PersonService {
    List<PersonDTO> findAll();
    PersonDTO findById(Integer id);
    List<PersonDTO> findAllSortedBy(String sortBy);
}
