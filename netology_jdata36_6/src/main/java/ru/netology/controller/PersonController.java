package ru.netology.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.model.Person;
import ru.netology.service.PersonService;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/persons/by-city")
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
        public List<Person> getPersonsFromOneCity(@RequestParam("city") String city) {
        return service.getPersonsByCity(city);
    }

    @GetMapping("/persons/younger-than-age") //younger-than-age
    @RolesAllowed({"READ"})
    public List<Person> getPersonsYoungerAge(@RequestParam("age") int age) {
        return service.getPersonsYoungerAge(age);
    }

    @GetMapping("/persons/by-name-surname")
    @Secured("ROLE_DELETE")
    public Optional<Person> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return service.getPersonsByNameAndSurname(name, surname);
    }

    @GetMapping("/persons/by-id")
    @RolesAllowed({"READ", "WRITE"})
    public Person getPersonById(@RequestParam("id") Long id) {
        return service.getPersonById(id);
    }

    @GetMapping("/persons/all-sort-by-surname")
    @PostAuthorize("hasRole('READ')")
    public List<Person> getPersonsSortBySurname(){
        return service.getPersonsSortBySurname();
    }

    @GetMapping("/all-cities")
    public List<String> getAllCities() { return service.getAllCities(); }

    @PreAuthorize("isAuthenticated().#username == authentication.principal.username")
    @GetMapping("/hi")
    public String getHi1(@RequestParam String username) {
        return  "Hello, " + username; }

}
