package com.betrybe.agrix.dto;


import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.security.Role;


/**
 * DTO PersonDto.
 */
public record PersonDto(Long id, String username, String password, Role role) {
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(person.getId(), person.getUsername(), person.getPassword(),
        person.getRole());
  }

  /**
   * toEntity.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);

    return person;
  }
}
