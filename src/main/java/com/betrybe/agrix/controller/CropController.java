package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class CropController.
 */
@RestController
@RequestMapping()
public class CropController {
  private final CropService cropService;
  private final FarmService farmService;

  private final FertilizerService fertilizerService;

  /**
   * Autowired.
   */
  @Autowired
  public CropController(CropService cropService, FarmService farmService,
      FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Método createFarm.
   */
  @GetMapping("/crops")
  public ResponseEntity<List<CropDto>> findAll() {
    List<Crop> crop = cropService.getAllCrops();
    List<CropDto> cropDtos = crop.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Método getFarmById.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    Crop crop = optionalCrop.get();

    CropDto cropDto = CropDto.fromEntity(crop);

    return ResponseEntity.ok(cropDto);
  }

  /**
   * Método createCrop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> insertCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {

    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    Crop cropsAdd = cropDto.toEntity();

    cropsAdd.setFarm(optionalFarm.get());

    Crop newCrop = cropService.insertCrop(cropsAdd);
    CropDto response = CropDto.fromEntity(newCrop);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Método getFarmById.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    List<Crop> crop = optionalFarm.get().getCrops();
    List<CropDto> cropDtos = crop.stream().map(CropDto::fromEntity).toList();

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Método searchCrop.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<?> searchCrop(@RequestParam String start,
      @RequestParam String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    List<Crop> cropsInRange = cropService.findCropByDate(startDate, endDate);

    List<CropDto> cropDto = cropsInRange.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropDto);
  }

  /**
   * Método associateFertilizer.
   */
  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<?> associateFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);
    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }

    fertilizerService.setAssociationCropFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");

  }

  /**
   * Método getFertilizerByCropId.
   */
  @GetMapping("/crops/{cropId}/fertilizers")
  public ResponseEntity<?> getFertilizerByCropId(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);
    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    List<Fertilizer> fertilizer = optionalCrop.get().getFertilizers();
    List<FertilizerDto> fertilizerDtos = fertilizer
        .stream().map(FertilizerDto::fromEntity).toList();

    return ResponseEntity.ok(fertilizerDtos);

  }
}
