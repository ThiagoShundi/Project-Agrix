package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.entities.repositories.CropRepository;
import com.betrybe.agrix.model.entities.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class FertilizerService.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  private final CropRepository cropRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository,
      CropRepository cropRepository) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizer() {
    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> getFertilizerById(Long id) {
    return fertilizerRepository.findById(id);
  }

  /**
   * Método setAssociationCropFertilizer.
   */
  public void setAssociationCropFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> crop = cropRepository.findById(cropId);
    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);

    if (crop.isPresent() && fertilizer.isPresent()) {
      crop.get().getFertilizers().add(fertilizer.get());

      cropRepository.save(crop.get());
    }

  }
}
