package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * DTO CropDto.
 */
public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate, Long farmId) {
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
        crop.getHarvestDate(), crop.getFarm().getId());
  }

  /**
   * toEntity.
   */
  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setId(id);
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setHarvestDate(harvestDate);

    return crop;
  }
}
