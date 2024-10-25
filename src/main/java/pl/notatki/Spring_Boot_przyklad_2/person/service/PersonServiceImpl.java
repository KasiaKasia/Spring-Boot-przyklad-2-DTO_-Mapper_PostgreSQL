package pl.notatki.Spring_Boot_przyklad_2.person.service;

import org.springframework.stereotype.Service;
import pl.notatki.Spring_Boot_przyklad_2.person.dto.PersonDTO;
import pl.notatki.Spring_Boot_przyklad_2.person.mapper.PersonMapper;
import pl.notatki.Spring_Boot_przyklad_2.person.model.Person;
import pl.notatki.Spring_Boot_przyklad_2.person.repository.PersonRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    private PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDTO> findAll() {
        return personMapper.mapToDTO(personRepository.findAll()) ;
    }

    public PersonDTO findById(Integer id) {
         Person person = personRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Person with given id does not exist"));
         return personMapper.mapToDTO(person);
    }

    @Override
    public List<PersonDTO> findAllSortedBy(String sortBy) {
        List<Person> persons = personRepository.findAll(); // Pobranie wszystkich danych z bazy danych
        return persons.stream()
                .map(personMapper::mapToDTO)
                .sorted(getComparator(sortBy)) // Sortowanie według wybranego kryterium
                .collect(Collectors.toList());
    }

    private Comparator<PersonDTO> getComparator(String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "firstname":
                return Comparator.comparing(PersonDTO::getFirstName);
            case "lastname":
                return Comparator.comparing(PersonDTO::getLastName);
            case "id":
                return Comparator.comparing(PersonDTO::getId);
            default:
                throw new IllegalArgumentException("Invalid sort parameter: " + sortBy);
        }
    }
}
