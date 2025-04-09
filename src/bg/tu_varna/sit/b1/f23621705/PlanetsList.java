package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.exceptions.DuplicatePlanetException;
import bg.tu_varna.sit.b1.f23621705.exceptions.NoPlanetException;

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

    public List<Planet> getPlanets() {
        return planets;
    }

    public Planet searchPlanet(String planetName) throws NoPlanetException {
        if (planets.isEmpty()) {
            throw new NoPlanetException("There is no planets!");
        } else {
            for (Planet planet : planets) {
                if (planet.getName().equals(planetName)) {
                    return planet;
                }
            }
            return null;
        }
    }

    public Jedi searchJedi(String name) throws NoPlanetException {
        if(planets.isEmpty()){
            throw new NoPlanetException("There is no planets!");
        }else{
            for(Planet planet: planets){
                for(Jedi jedi:planet.getJedis()){
                    if(jedi.getName().equals(name)){
                        return jedi;
                    }
                }
            }
            return null;
        }
    }
}
