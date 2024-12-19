package nl.pink.pinkproject.controller;

import lombok.RequiredArgsConstructor;
import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.data.projection.SortedProjection;
import nl.pink.pinkproject.dto.CreatePersonRequest;
import nl.pink.pinkproject.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<SortedProjection>> getAll(@RequestParam(required = false) SortType sortType) {
        return ResponseEntity.ok(personService.getAllSortedBy(sortType));
    }

    @GetMapping
    public ResponseEntity<Person> getPerson(@RequestParam Long id) {
        Person person = personService.get(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody CreatePersonRequest request) {
        return ResponseEntity.ok(personService.save(request));
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.update(person));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam Long id) {
        try {
            personService.delete(id);
            return ResponseEntity.ok("Deleted person with id: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Person with id: " + id + " does not exist");
        }
    }



}
