package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class PersonService.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public PersonService(
      PersonRepository personRepository, PasswordEncoder passwordEncoder) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Returns a person for a given username.
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> person = personRepository.findByUsername(username);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Creates a new person.
   */
  public Person create(Person person) {
    String hashedPassword = passwordEncoder.encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getPersonByUsername(username);
  }
}
