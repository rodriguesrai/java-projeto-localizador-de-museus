package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean coordinateIsValid = CoordinateUtil.isCoordinateValid(coordinate);
    if (!coordinateIsValid) {
      throw new InvalidCoordinateException();
    }
    return museumFakeDatabase.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    boolean coordinateIsValid = CoordinateUtil.isCoordinateValid(museum.getCoordinate());
    if (!coordinateIsValid) {
      throw new InvalidCoordinateException();
    }
    return museumFakeDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return museumFakeDatabase.getMuseum(id)
        .orElseThrow(MuseumNotFoundException::new);
  }
}
