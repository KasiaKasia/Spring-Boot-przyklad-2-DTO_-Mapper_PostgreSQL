package pl.notatki.Spring_Boot_przyklad_2.person.mapper;

import org.springframework.stereotype.Component;
import pl.notatki.Spring_Boot_przyklad_2.person.dto.PersonDTO;
import pl.notatki.Spring_Boot_przyklad_2.person.model.Person;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public PersonDTO mapToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        return personDTO;
    }
    public List<PersonDTO> mapToDTO(Collection<Person> persons) {

        return persons.stream() // persons.stream()- tworzy strumień (Stream) z kolekcji persons
                // map() to operacja, która przekształca elementy strumienia.
                // W tym przypadku każdy obiekt typu Person jest przekazywany do metody mapToDTO, która konwertuje go na PersonDTO
                .map(this::mapToDTO)
                // collect() zbiera wyniki przekształconego strumienia i tworzy z niego listę
                .collect(Collectors.toList());
    }
}
