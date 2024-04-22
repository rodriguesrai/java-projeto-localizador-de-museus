package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Create museum response entity.
   *
   * @param museumDto the museum dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumDto);
    Museum createdMuseum = museumService.createMuseum(museum);
    MuseumDto createdMuseumDto = ModelDtoConverter.modelToDto(createdMuseum);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMuseumDto);
  }

  /**
   * Gets closest museum.
   *
   * @param latitude      the latitude
   * @param longitude     the longitude
   * @param maxDistanceKm the max distance km
   * @return the closest museum
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam(name = "lat") Double latitude,
      @RequestParam(name = "lng") Double longitude,
      @RequestParam(name = "max_dist_km") Double maxDistanceKm) {

    Museum closestMuseum = museumService.getClosestMuseum(
        new Coordinate(latitude, longitude), maxDistanceKm);
    MuseumDto closestMuseumDto = ModelDtoConverter.modelToDto(closestMuseum);

    return ResponseEntity.ok().body(closestMuseumDto);
  }
}
