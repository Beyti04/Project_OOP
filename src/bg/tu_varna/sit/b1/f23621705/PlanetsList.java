package bg.tu_varna.sit.b1.f23621705;

import java.util.ArrayList;
import java.util.List;

public class PlanetsList implements PlanetCreator{
    private static PlanetsList planetsInstance;
    private List<Planet> planets;

    private PlanetsList(){
        planets=new ArrayList<>();
    }

    public static PlanetsList getPlanetsInstance() {
        if (planetsInstance == null) {
            planetsInstance = new PlanetsList();
        }
        return planetsInstance;
    }

    @Override
    public void addPlanet(Planet planet) throws DuplicatePlanetException {
        if(planets.stream().anyMatch(planet1->planet1.getName().equals(planet.getName()))) {
            throw new DuplicatePlanetException("Planet already exists!");
        }else{
            planets.add(planet);
        }
    }

    public Planet searchPlanet(String planetName) throws NoPlanetException {
        if (planets.isEmpty()) {
            throw new NoPlanetException("There is no planet with this name!");
        } else {
            for (Planet planet : planets) {
                if (planet.getName().equals(planetName)) {
                    return planet;
                }
            }
            return null;
        }
    }

}
