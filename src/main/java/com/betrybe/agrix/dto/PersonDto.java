package com.betrybe.agrix.dto;


import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;


/**
 * DTO PersonDto.
 */
public record PersonDto(String username, String password, Role role) {

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

  /**
   * ToResponse.
   */
  public static record ToResponse(Long id, String username, Role role) {

    /**
     * fromEntity.
     */
    public static ToResponse fromEntity(Person person) {
      return new ToResponse(person.getId(), person.getUsername(), person.getRole());
    }
  }
}
