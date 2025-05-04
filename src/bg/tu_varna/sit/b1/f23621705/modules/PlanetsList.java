package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.interfaces.PlanetCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.PlanetRemover;

import java.util.ArrayList;
import java.util.List;

public class PlanetsList implements PlanetCreator, PlanetRemover {
    private static PlanetsList planetsInstance;
    private final List<Planet> planets;

    private PlanetsList() {
        planets = new ArrayList<>();
    }

    public static PlanetsList getPlanetsInstance() {
        if (planetsInstance == null) {
            planetsInstance = new PlanetsList();
        }
        return planetsInstance;
    }

    @Override
    public void createPlanet(Planet planet) {
        if (planets.stream().anyMatch(planet1 -> planet1.getName().equals(planet.getName()))) {
            System.out.println("Planet already exists!");
        } else {
            planets.add(planet);
        }
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Planet getPlanet(String planetName) {
        if (planets.isEmpty()) {
            return null;
        } else {
            for (Planet planet : planets) {
                if (planet.getName().equals(planetName)) {
                    return planet;
                }
            }
        }
        return null;
    }

    @Override
    public void removePlanet(String planetName) {
        if (getPlanet(planetName) != null) {
            planets.remove(getPlanet(planetName));
        }
    }

    public void removeAll(){
        planets.removeAll(planets);
    }

    public Jedi getJedi(String name) {
        if (planets.isEmpty()) {
            System.out.println("There is no planets!");
        } else {
            for (Planet planet : planets) {
                for (Jedi jedi : planet.getJedis()) {
                    if (jedi.getName().equals(name)) {
                        return jedi;
                    }
                }
            }
        }
        return null;
    }
}
