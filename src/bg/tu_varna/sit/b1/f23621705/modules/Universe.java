package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.interfaces.PlanetCreator;

import java.util.ArrayList;
import java.util.List;

public class Universe implements PlanetCreator {
    private static Universe planetsInstance;
    private final List<Planet> planets;

    private Universe() {
        planets = new ArrayList<>();
    }

    /**
     * Функция за вземане на инстанцията на сингълтън списък с планети
     *
     * @return Връща инстанцията на сингълтън класа
     */
    public static Universe getUniverseInstance() {

        if (planetsInstance == null) {

            planetsInstance = new Universe();

        }
        return planetsInstance;
    }

    /**
     * Добавя планета към списъка
     *
     * @param planet параметър за планета
     */
    @Override
    public void createPlanet(Planet planet) {

        if (planets.stream().anyMatch(planet1 -> planet1.getName().equals(planet.getName()))) {

            System.out.println("Planet already exists!");

        } else {

            planets.add(planet);

        }
    }

    /**
     * Връща списък с планети
     * @return
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Взема дадена планета по име
     *
     * @param planetName параметър за име на планета
     * @return Връща обект от тип Planet ако съществува
     */
    public Planet getPlanet(String planetName) {

        if (planets.isEmpty()) {

            return null;

        } else {

            for (Planet planet : planets) {

                if (planet.getName().equalsIgnoreCase(planetName)) {

                    return planet;

                }
            }

        }

        return null;
    }

    /**
     * Премахва всички планети
     */
    public void removeAll() {
        planets.removeAll(planets);
    }

    /**
     * Взима джедай с дадено име
     *
     * @param name параметър за име на джедай
     * @return Връща обект от типа Jedi ако съществува
     */
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
