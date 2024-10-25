package pl.notatki.Spring_Boot_przyklad_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.notatki.Spring_Boot_przyklad_2.person.dto.PersonDTO;
import pl.notatki.Spring_Boot_przyklad_2.person.service.PersonService;
import java.util.List;

@SpringBootApplication
public class SpringBootPrzyklad2Application {

	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(SpringBootPrzyklad2Application.class, args);
		PersonService personService = appContext.getBean(PersonService.class); // pobraie Bean

		List<PersonDTO> persons = personService.findAll();
		persons.forEach(System.out::println);

		PersonDTO thirdPerson = personService.findById(3);
		System.out.println("Id = 3 : "+ thirdPerson);


		List<PersonDTO> sortedById = personService.findAllSortedBy("id");
		System.out.println("Osoby posortowane według id:");
		sortedById.forEach(System.out::println);
	}
}
