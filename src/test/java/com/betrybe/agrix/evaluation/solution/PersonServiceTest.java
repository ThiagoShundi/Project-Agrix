package com.betrybe.agrix.evaluation.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @Test
  public void testGetPersonById() {
    Person person = new Person();
    person.setUsername("Thiago");
    person.setPassword("senha");
    person.setRole(Role.USER);

    Person savedPerson = personService.create(person);

    Person personByid = personService.getPersonById(savedPerson.getId());

    assertNotNull(savedPerson.getId());
    assertEquals(person.getUsername(), personByid.getUsername());
    assertEquals(person.getPassword(), personByid.getPassword());
    assertEquals(person.getRole(), personByid.getRole());
  }

  @Test
  public void testGetPersonByUserName() {
    Person person = new Person();
    person.setUsername("Thiago12");
    person.setPassword("senha12");
    person.setRole(Role.USER);

    Person savedPerson = personService.create(person);

    Person personByUserName = personService.getPersonByUsername(savedPerson.getUsername());

    assertNotNull(personByUserName.getId());
    assertEquals(personByUserName.getUsername(), person.getUsername());
    assertEquals(personByUserName.getPassword(), person.getPassword());
    assertEquals(personByUserName.getRole(), person.getRole());
  }

  @Test
  public void testCreate() {
    Person person = new Person();
    person.setUsername("Thiago123");
    person.setPassword("senha123");
    person.setRole(Role.ADMIN);

    Person savedPerson = personService.create(person);

    assertNotNull(savedPerson.getId());
    assertEquals(savedPerson.getUsername(), person.getUsername());
    assertEquals(savedPerson.getPassword(), person.getPassword());
    assertEquals(savedPerson.getRole(), person.getRole());
  }
}
