package com.betrybe.agrix.model.entities.repositories;

import com.betrybe.agrix.model.entities.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface PersonRepository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByUsername(String username);
}
