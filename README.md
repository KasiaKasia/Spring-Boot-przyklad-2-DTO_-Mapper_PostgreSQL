# Spring Boot - expamle 2

The initial version of the project is generated from https://start.spring.io/

## Steps to run the application using terminal

1. Clone the repository
 
2. In the project directory, run the following commands:

`./mvnw spring-boot:run` 

3. Next, open a web browser and navigate to the following address: http://localhost:8085

## Steps to run the application using main class

1. Clone the repository

2. Go to the SpringBootPrzyklad2Application class, click the green triangle next to the class name or click Ctrl+Shift+F10

3. Next, open a web browser and navigate to the following address: http://localhost:8085


## SPRING BOOT
SPRING BOOT-to połączenie Spring, konfiguracji i kontenera aplikacji. 

## SPRING DATA
Spring jest zestawem kilkunastu projektów. Jednym z nich jest Spring Data, sam się dzieli na kilka modułów. Spring Data ma ułatwić dostęp do różnego rodzaju danych.
Jednym z modułów Spring Data jest Spring Data JPA (Java Persistence API)



## Spring Data JPA (Java Persistence API)
Spring Data JPA to moduł, pomaga w użyciu ORM (Object-Relational Mapping) opartych na Java Persistence API (JPA). Jest dodatkową nakładką która ułatwia pewne operacje .

Spring Data JPA domyślnie używa Hibernate. Wprowadza dodatkową warstwę abstrakcji do kodu.
Dzięki temu możemy się pozbyć prawie całkowicie kodu połączeniowego z bazą danych.
Jednym z najczęściej wykorzystywanych ficzerów w Spring Data JPA są repozytoria, które upraszczają prace z bazą danych.

## Połączenie i inicjalizacją z bazą danych PostgreSQL

1. Dodanie w pliku pom.xml zależności:
 
  `<dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>`

  `<dependency>
   		<groupId>org.postgresql</groupId>
   		<artifactId>postgresql</artifactId>
   		<scope>runtime</scope>
   </dependency>`

2. Połączenie  z bazą danych wykonuje się w pliku :`application.properties` za pomocą opcji:

`spring.datasource.url=jdbc:postgresql://localhost:5433/db`
`spring.datasource.username=postgres`
`spring.datasource.password=postgres`

3. Dodanie tabeli do bazy danych wykonuje się za pomocą klasy definiującej pola jako nazwy kolumn w tabeli. Klasa musi zawierać adnotację:
   `@Entity`
   `@Table(name="person")`
4. Należy także dodać konfiguracje w pliku `application.properties` z opcją:
`spring.jpa.hibernate.ddl-auto=create-drop`
 
Opcja `spring.jpa.hibernate.ddl-auto` ma właściwości:
`none`: brak zmian w schemacie bazy danych.
`validate`: weryfikacja zgodności schematu bazy z encjami.
`update`: aktualizacja schematu bez usuwania istniejących tabel/kolumn.
`create`: tworzenie nowych tabel przy każdym uruchomieniu aplikacji, usuwa istniejące.
`create-drop`: tworzenie tabel przy starcie aplikacji i usunięcie ich przy jej zamknięciu.

5. Dodając skrypt z domyślna nazwa `data.sql` w katalogu: 
`..\IdeaProjects\Spring-Boot-przyklad-2\src\main\resources\`
spowoduję uruchomienie się jego, przy starcie aplikacji.
Jednak należy pamiętać, że wykonuję się przed inicjalizacją Hibernate. Taka konfiguracja jest przydatna wtedy gdy używamy narzędzi do migracji bazy danych.
U nas jednak za każdym razem jest tworzony nowy schemat bazy danych, który jest wygenerowany przez Hibernate.
Dlatego w pliku `application.properties` musimy ustawić dodatkowe właściwości:
- `spring.sql.init.mode=always` 
to ustawienie sprawia, że Spring Boot zawsze wykonuje skrypty SQL (zarówno `schema.sql`, jak i `data.sql`) podczas inicjalizacji aplikacji.
- `spring.jpa.defer-datasource-initialization=true`
to ustawienie modyfkuje domyślnie zachowanie Spring Boot i wypełnia baze danych, danymi dopiero po wygenerowaniu schematu bazy danych przez Hibernate

## DTO (Data Transfer Object)
Na początku nasz serwis zwracał ten sam typ obiektu, który był encją w bazie danych. Na tym poziomie w tej warstwie powinniśmy używać innego obiektu, tzn DTO (Data Transfer Object)
DTO w Spring Boot to wzorzec projektowy używany do przesyłania danych między różnymi warstwami aplikacji, szczególnie między warstwą serwisową a warstwą kontrolerów.

Zastosowanie tego wzorca jest przydatne w przypadku, kiedy zachodzą np.: zmiany w bazie danych, a nie ma potrzeby ich przekazywać do obiektu DTO.


## STEROTYPE
STEROTYPE - specjalny typ adnotacji mówiący, że dana klasa jest Bean i wskazuje, jaką rolę taka klasa pełni w całej aplikacji.

## PODZIAŁ STEROTYPE:
- `@Component` - jest to adnotacja, po której dziedziczą wszystkie STEROTYPE. Oznaczenie klasy tą adnotacją, nie daje nam dodatkowej informacji poza tym, iż jest zarządzany Spring. Można powiedzieć, że @Componet jest odpowiednikiem @Bean, ale zarządzany na poziomie klasy
- `@Repository` - oznacza, że dana klasa jest odpowiedzialna za operacje na danych, a dokładnie zapewnia hermetyzacje przechowywania, pobierania i wyszukiwania informacji symulując tym samym kolekcję obiektów.
  Taki typ klasy nadaje się idealnie do opakowania operacji na bazie danych,
- `@Service` - są to klasy, w których implementowana jest logika biznesowa aplikacji,
- `@Controller` - służy do oznaczenia klasy obsługującej żądania HTTP,
- `@ComponentScan` - oznaczenie danej klasy odpowiednią adnotacją sterotype nie spowoduję jeszcze, że Spring dla takiej klasy sam stworzy Bean. Musimy jeszcze dodatkowo wskazać Springowi, gdzie ma szukać adnotowanych klas.
  Do tego służy właśnie klasa adnotowana @ComponentScan. Zawiera się w @SpringBootApplication. Jeszcze inaczej to jest to mechanizm pozwalający na automatyczne znajdowanie klas oznaczonych adnotacjami, takimi jak @Component, @Service, @Repository, czy @Controller, i rejestrowanie ich jako beany w kontekście aplikacji.




