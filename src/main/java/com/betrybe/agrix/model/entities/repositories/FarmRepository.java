package com.betrybe.agrix.model.entities.repositories;

import com.betrybe.agrix.model.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface FarmRepository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
