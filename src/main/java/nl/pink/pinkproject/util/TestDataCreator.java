package nl.pink.pinkproject.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestDataCreator {

    private final PersonService personService;

    Faker faker;

    @PostConstruct
    public void init() {
        faker = new Faker();
    
        for (int i = 0; i < 100; i++) {
            Person person = createRandomPerson();
            
            // Randomly create a partner for some people
            if (faker.random().nextBoolean()) {
                Person partner = createRandomPerson();
                person.setPartner(partner);
                partner.setPartner(person);
                personService.save(person);  // Save the updates
                personService.save(partner); // Save the partner

                // Randomly create children for some people
                if (faker.random().nextBoolean()) {
                    int numChildren = faker.random().nextInt(1, 4); // Up to 3 children
                    for (int j = 0; j < numChildren; j++) {
                        createChild(person, partner);
                    }
                }

            }
    

        }
    }

    private Person createRandomPerson() {
        Person person = new Person();
        person.addName(faker.name().firstName());
        LocalDate birthday = faker.timeAndDate().birthday(1, 60);
        person.setBirthdate(birthday);
        person = personService.save(person);
        log.info("Created person: {}", person);
        return person;
    }
    
    private Person createChild(Person parent, Person partner) {
        Person child = new Person();
        child.addName(faker.name().firstName());
        LocalDate childBirthday = faker.timeAndDate().birthday(0, 18); // Age range for children
        child.setBirthdate(childBirthday);
        child.setParent1(parent);
        child.setParent2(partner);
        child = personService.save(child);

        parent.addChild(child);
        partner.addChild(child);
        personService.save(parent);
        personService.save(partner);
        return child;
    }



}
