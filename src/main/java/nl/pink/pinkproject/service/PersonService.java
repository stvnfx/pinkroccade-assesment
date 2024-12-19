package nl.pink.pinkproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pink.pinkproject.controller.SortType;
import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.data.projection.SortedProjection;
import nl.pink.pinkproject.data.repository.PersonRepository;
import nl.pink.pinkproject.dto.CreatePersonRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(CreatePersonRequest request) {
        Person person = new Person();
        person.addName(request.name());
        person.setBirthdate(request.birthDate());
        person.setParent1(request.parent1Id() != null ? get(request.parent1Id()) : null);
        person.setParent2(request.parent2Id() != null ? get(request.parent2Id()) : null);
        person.setPartner(request.partnerId() != null ? get(request.partnerId()) : null);
        return personRepository.save(person);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person get(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<SortedProjection> getAllSortedBy(SortType sortType) {
        Collection<SortedProjection> all = personRepository.findAllSorted();

        if(sortType == SortType.NAME) {
            return all.stream().sorted(Comparator.comparing(e -> e.getNames().get(0))).toList();
        } else if (sortType == SortType.ID) {
            return all.stream().sorted(Comparator.comparing(SortedProjection::getId)).toList();
        }
        return all.stream().toList();
    }

    public Person update(Person person) {
        log.info("Updating person: {}", person);
        Person existingPerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new IllegalArgumentException("Person with id " + person.getId() + " does not exist"));
        if (person.getNames() != null && !person.getNames().isEmpty()) {
            existingPerson.setNames(person.getNames());
        }
        if (person.getBirthdate() != null) {
            existingPerson.setBirthdate(person.getBirthdate());
        }
        if (person.getParent1() != null) {
            existingPerson.setParent1(person.getParent1());
        }
        if (person.getParent2() != null) {
            existingPerson.setParent2(person.getParent2());
        }
        if (person.getPartner() != null) {
            existingPerson.setPartner(person.getPartner());
        }
        if (person.getChildren() != null && !person.getChildren().isEmpty()) {
            existingPerson.setChildren(person.getChildren());
        }
        return personRepository.save(existingPerson);
    }

    public void delete(Long personId) {
        if(personRepository.existsById(personId)) {
            personRepository.findById(personId).ifPresent(person -> {
                person.removeAllReferences();
                personRepository.save(person);
            });
            personRepository.deleteById(personId);
        } else {
            throw new IllegalArgumentException("Person with id " + personId + " does not exist");
        }
    }


    //The user can get a list of all persons who have a partner and three children with that partner and one
    //of the children has an age below 18
    public List<Person> filter3a() {
        Stream<Person> stream = StreamSupport.stream(personRepository.findAll().spliterator(), false);
        Stream<Person> personStream = stream
                .filter(person -> person.getPartner() != null)
                .peek(person -> log.info("Partner is not null: {}", person))
                .filter(person -> person.getChildren().size() > 3)
                .peek(person -> log.info("Has more than 3 children: {}", person))
                .filter(person -> person.getChildren().stream().anyMatch(child -> isPartnerAlsoParent(person, person.getPartner(), child)))
                .peek(person -> log.info("Partner is also a parent for one of the children: {}", person))
                .filter(person -> person.getChildren().stream().anyMatch(child -> calculateAge(child) < 18))
                .peek(person -> log.info("Has at least one child under 18: {}", person));
        List<Person> list = personStream.toList();
        log.info("personStream.toList(): {}", list);
        return list;
    }

    public boolean isPartnerAlsoParent(Person person, Person partner, Person child) {
        Person parent1 = child.getParent1();
        Person parent2 = child.getParent2();

        if(person.equals(parent1) || person.equals(parent2)) {
            return partner.equals(parent1) || partner.equals(parent2);
        } else {
            return false;
        }
    }

    public boolean isSamePartner(Person person1, Person person2) {
        return person1.getPartner() != null && person1.getPartner().equals(person2);
    }

    public boolean isSameParent(Person person1, Person person2) {
        return person1.getParent1() != null && person1.getParent1().equals(person2) ||
                person1.getParent2() != null && person1.getParent2().equals(person2);
    }

    public int calculateAge(Person person) {
        return person.getBirthdate().until(java.time.LocalDate.now()).getYears();
    }

    public String csvConverter(List<Person> persons) {
        StringBuilder sb = new StringBuilder();

        sb.append("id,names,birthdate\n");
        persons.forEach(person -> {
            sb.append(person.getId()).append(",");
            sb.append(person.getNames().get(0)).append(",");
            sb.append(person.getBirthdate()).append("\n");
        });

        return sb.toString();
    }

    public String base64ConverterString(String csvString) {
        return Base64.getEncoder().encodeToString(csvString.getBytes());
    }


}
