package com.betrybe.agrix.controller;


import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class PersonController.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Método createFertilizer.
   */
  @PostMapping()
  public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toEntity());
    PersonDto.ToResponse response = PersonDto.ToResponse.fromEntity(newPerson);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }
}
