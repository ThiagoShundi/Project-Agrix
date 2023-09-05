package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * DTO FertilizerDto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
        fertilizer.getComposition());
  }

  /**
   * toEntity.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setId(id);
    fertilizer.setName(name);
    fertilizer.setBrand(brand);
    fertilizer.setComposition(composition);

    return fertilizer;
  }
}
