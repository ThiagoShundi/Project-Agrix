package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * DTO FarmDTO.
 */
public record FarmDto(Long id, String name, Double size) {
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
   * toEntity.
   */
  public Farm toEntity() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);

    return farm;
  }
}
