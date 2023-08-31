package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Class FertilizerController.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Método createFertilizer.
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizerDto.toEntity());
    FertilizerDto response = FertilizerDto.fromEntity(newFertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }

  /**
   * Método createFertilizer.
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> fertilizer = fertilizerService.getAllFertilizer();
    List<FertilizerDto> fertilizerDtos = fertilizer
        .stream().map(FertilizerDto::fromEntity).toList();
    return ResponseEntity.ok(fertilizerDtos);
  }

  /**
   * Método getFertilizerById.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(id);

    if (optionalFertilizer.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }

    Fertilizer fertilizer = optionalFertilizer.get();

    FertilizerDto fertilizerDto = FertilizerDto.fromEntity(fertilizer);

    return ResponseEntity.ok(fertilizerDto);
  }
}
