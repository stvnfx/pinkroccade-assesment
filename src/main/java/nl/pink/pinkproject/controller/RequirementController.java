package nl.pink.pinkproject.controller;

import lombok.RequiredArgsConstructor;
import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RequirementController {
    private final PersonService personService;

    @GetMapping("/3a")
    public List<Person> get3a() {
        return personService.filter3a();
    }

    //The list should be returned as base64 encoded CSV file
    @GetMapping("/3b")
    public ResponseEntity<String> get3b() {
        return ResponseEntity.ok(personService.base64ConverterString(personService.csvConverter(personService.filter3a())));
    }
}
