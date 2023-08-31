package com.betrybe.agrix.model.entities.repositories;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CropRepository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}