package bg.tu_varna.sit.b1.f23621705.interfaces;

import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.exceptions.DuplicatePlanetException;

public interface PlanetCreator {
    void addPlanet(Planet planet) throws DuplicatePlanetException;
}
