package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.interfaces.PlanetCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.PlanetRemover;

public class PlanetManager implements PlanetCreator, PlanetRemover {

    @Override
    public void createPlanet(Planet planet) {
        PlanetsList.getPlanetsInstance().createPlanet(planet);
    }

    @Override
    public void removePlanet(String planetName) {
        if(PlanetsList.getPlanetsInstance().getPlanet(planetName)==null){
            System.out.println("There is no planet with this name");
        }else{
            PlanetsList.getPlanetsInstance().getPlanets().remove(PlanetsList.getPlanetsInstance().getPlanet(planetName));
            System.out.println("Planet "+planetName+" has been successfully removed!");
        }
    }

    public Planet getPlanet(String planetName){
        return PlanetsList.getPlanetsInstance().getPlanet(planetName);
    }


}
