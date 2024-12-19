package nl.pink.pinkproject.service;

import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.data.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void testIsPartnerAlsoParent_PartnerIsAlsoParent() {
        Person person = new Person();
        person.setId(1L);

        Person partner = new Person();
        partner.setId(2L);

        Person child = new Person();
        child.setId(3L);
        child.setParent1(person);
        child.setParent2(partner);

        boolean result = personService.isPartnerAlsoParent(person, partner, child);
        assertTrue(result);
    }

    @Test
    void testIsPartnerAlsoParent_PartnerIsNotParent() {
        Person person = new Person();
        person.setId(1L);

        Person partner = new Person();
        partner.setId(2L);

        Person child = new Person();
        child.setId(3L);
        child.setParent1(person);
        child.setParent2(new Person());

        boolean result = personService.isPartnerAlsoParent(person, partner, child);
        assertFalse(result);
    }

    @Test
    void testIsPartnerAlsoParent_NeitherIsParent() {
        Person person = new Person();
        person.setId(1L);

        Person partner = new Person();
        partner.setId(2L);

        Person child = new Person();
        child.setId(3L);
        child.setParent1(new Person());
        child.setParent2(new Person());

        boolean result = personService.isPartnerAlsoParent(person, partner, child);
        assertFalse(result);
    }

    @Test
    void testIsPartnerAlsoParent_OneParentIsNull() {
        Person person = new Person();
        person.setId(1L);

        Person partner = new Person();
        partner.setId(2L);

        Person child = new Person();
        child.setId(3L);
        child.setParent1(person);
        child.setParent2(null);

        boolean result = personService.isPartnerAlsoParent(person, partner, child);
        assertFalse(result);
    }

//    @Test
//    void testFilter3a() {
//        // Mock the personRepository
//        PersonRepository mockRepository = mock(PersonRepository.class);
//        personService = new PersonService(mockRepository);
//
//        Person person1 = new Person();
//        person1.setId(1L);
//        person1.setPartner(new Person());
//        person1.setChildren(Set.of(
//                createChildWithAgeAndParents(5, person1, person1.getPartner(), 2L)
//        ));
//
//        Person person2 = new Person();
//        person2.setId(2L);
//        person2.setPartner(new Person());
//        person2.setChildren(Set.of(
//                createChildWithAgeAndParents(20, person2, person2.getPartner(), 3L),
//                createChildWithAgeAndParents(25, person2, person2.getPartner(), 4L),
//                createChildWithAgeAndParents(30, person2, person2.getPartner(), 5L),
//                createChildWithAgeAndParents(35, person2, person2.getPartner(), 6L)
//        ));
//
//        Person person3 = new Person();
//        person3.setId(3L);
//        person3.setPartner(null); // No partner
//        person3.setChildren(Set.of(createChildWithAgeAndParents(10, person3, null, 7L)));
//
//        when(mockRepository.findAll()).thenReturn(List.of(person1, person2, person3));
//
//        List<Person> result = personService.filter3a();
//
//        assertTrue(result.contains(person1)); // person1 should pass all filters
//        assertFalse(result.contains(person2)); // person2 fails because no child <18
//        assertFalse(result.contains(person3)); // person3 fails because no partner
//    }

    private Person createChildWithAgeAndParents(int age, Person parent1, Person parent2, Long childId) {
        Person child = new Person();
        child.setId(childId);
        child.setParent1(parent1);
        child.setParent2(parent2);
        child.setBirthdate(LocalDate.now().minusYears(age));
        return child;
    }

    @Test
    void testCalculateAge_ValidBirthdate() {
        Person person = new Person();
        person.setBirthdate(LocalDate.of(2000, 1, 1)); // Set birthdate to a valid past date

        int age = personService.calculateAge(person);

        assertTrue(age > 0); // Assert that the calculated age is a positive number
    }

    @Test
    void testCalculateAge_BirthdateInFuture() {
        Person person = new Person();
        person.setBirthdate(LocalDate.now().plusYears(1)); // Set birthdate to a future date

        int age = personService.calculateAge(person);

        assertTrue(age < 0); // Assert that the calculated age is negative for future birthdates
    }

}