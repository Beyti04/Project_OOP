package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediRemover;

import java.util.*;

public class JediManager implements JediCreator, JediRemover {
    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    private final Universe universe = Universe.getUniverseInstance();

    /**
     * Създава нов джедай в двата и ги записва в съответните списъци
     *
     * @param jedi джедай, когото добавяме
     */
    @Override
    public void createJedi(Jedi jedi) {

        if (getJedi(jedi.getName()) == null) {

            universe.getPlanet(jedi.getPlanet().getName()).createJedi(jedi);

        } else {

            System.out.println("Jedi already exists! ");

        }
    }

    /**
     * Премахва джедай от списъците
     *
     * @param name       име на джедая, когото премахваме
     * @param planetName име на планета, в която търсим джедая
     */
    @Override
    public void removeJedi(String name, String planetName) {
        universe.getPlanet(planetName).removeJedi(name);
    }

    /**
     * Взема джедай ако съществува
     *
     * @param name име на джедай
     * @return Връща обект от типа Jedi ако съществува
     */
    public Jedi getJedi(String name) {

        if (universe.getJedi(name) != null) {

            return universe.getJedi(name);

        } else {

            return null;

        }
    }

    /**
     * Взема всички джедаи и ги добавя в списък
     *
     * @return връща списък с елементи от тип Jedi
     */
    public List<Jedi> getJedis() {

        List<Jedi> jedis = new ArrayList<>();

        for (Planet planet : universe.getPlanets()) {

            jedis.addAll(planet.getJedis());

        }

        return jedis;
    }

    /**
     * Повишава ранга и силата на джедай
     *
     * @param name       име на джедай
     * @param multiplier множител за увеличаване на силата
     */
    public void promoteJedi(String name, Double multiplier) {

        getJedi(name).setJediRank(getJedi(name).getJediRank().next());
        double strength = getJedi(name).getStrength();

        strength += (multiplier * strength);

        if (strength <= MAX_STRENGTH) {

            getJedi(name).setStrength(strength);

        } else {

            getJedi(name).setStrength(MAX_STRENGTH);

        }
    }

    /**
     * Понижава ранга и силата на джедай
     *
     * @param name       име на джедай
     * @param multiplier множител за намаляване на силата
     */
    public void demoteJedi(String name, Double multiplier) {

        getJedi(name).setJediRank(getJedi(name).getJediRank().prev());
        double strength = getJedi(name).getStrength();

        strength -= (multiplier * strength);

        if (strength <= MIN_STRENGTH) {

            getJedi(name).setStrength(strength);

        } else {

            getJedi(name).setStrength(MIN_STRENGTH);

        }
    }

    /**
     * Взема на силния джедай от дадена планета
     *
     * @param planetName име на планета
     * @return Връща най-силния джедай от планетата ако има джедаи на планетата
     */
    public Jedi getStrongestJedi(String planetName) {

        if (universe.getPlanet(planetName).getJedis() != null) {

            List<Jedi> jedis = universe.getPlanet(planetName).getJedis();
            Jedi currentStrongest = jedis.getFirst();

            for (Jedi jedi : jedis) {

                if (currentStrongest.getStrength() < jedi.getStrength()) {

                    currentStrongest = jedi;

                }

            }

            return currentStrongest;

        } else {

            return null;

        }
    }

    /**
     * Взема най-младия джедай от дадена планета със съответния ранг
     *
     * @param planetName име на планета
     * @param rank       ранг на групата, от която ще вземем най-младия джедай
     * @return Връща най-младия джедай от планетата
     */
    public Jedi getYoungestJedi(String planetName, JediRank rank) {

        if (universe.getPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {

            List<Jedi> jedis = new ArrayList<>();

            for (Jedi jedi : universe.getPlanet(planetName).getJedis()) {

                if (jedi.getJediRank().equals(rank)) {

                    jedis.add(jedi);

                }

            }

            return Collections.min(jedis, Comparator.comparing(Jedi::getAge).thenComparing(Jedi::getName));

        } else {

            return null;

        }
    }

    /**
     * Взема най-използвания цвят за светлинен меч на планетата
     *
     * @param planetName име на планета
     * @return Връща обект от тип LightsaberColour
     */
    public LightsaberColour mostUsedLightSaberColour(String planetName) {

        List<Jedi> jedis = universe.getPlanet(planetName).getJedis();
        Set<LightsaberColour> ValidColours = new HashSet<>();
        List<Jedi> allowedJedis = new ArrayList<>();
        Map<LightsaberColour, Integer> counter = new HashMap<>();

        LightsaberColour mostUsed = null;
        int maxCount = 0;

        for (Jedi jedi : jedis) {

            if (jedi.getJediRank().equals(JediRank.GRAND_MASTER)) {

                ValidColours.add(jedi.getLightsaberColour());

            }
        }

        for (Jedi jedi : jedis) {

            if (ValidColours.contains(jedi.getLightsaberColour())) {

                allowedJedis.add(jedi);

            }
        }

        for (Jedi jedi : allowedJedis) {

            counter.put(jedi.getLightsaberColour(), counter.getOrDefault(jedi.getLightsaberColour(), 0) + 1);

        }

        for (Map.Entry<LightsaberColour, Integer> entry : counter.entrySet()) {

            if (entry.getValue() > maxCount) {

                mostUsed = entry.getKey();
                maxCount = entry.getValue();

            }

        }

        return mostUsed;
    }

    /**
     * Взема най-използвания цвят за светлинен меч на планетата
     *
     * @param planetName име на планета
     * @param rank       ранг на групата, от която ще се вземе най-използвания цвят
     * @return Връща обект от типа LightsaberColour
     */
    public LightsaberColour mostUsedLightSaberColour(String planetName, JediRank rank) {

        List<Jedi> jedis = new ArrayList<>();
        LightsaberColour mostUsed = null;
        int maxCount = 0;

        for (Jedi jedi : universe.getPlanet(planetName).getJedis()) {

            if (jedi.getJediRank().equals(rank)) {

                jedis.add(jedi);

            }

        }

        Map<LightsaberColour, Integer> counter = new HashMap<>();

        for (Jedi jedi : jedis) {

            counter.put(jedi.getLightsaberColour(), counter.getOrDefault(jedi.getLightsaberColour(), 0) + 1);

        }

        for (Map.Entry<LightsaberColour, Integer> entry : counter.entrySet()) {

            if (entry.getValue() > maxCount) {

                mostUsed = entry.getKey();
                maxCount = entry.getValue();

            }

        }

        return mostUsed;
    }

    /**
     * Извежда името на планетата и населяващите я джедаи
     *
     * @param planetName име на планета
     * @return Връща обект от типа String
     */
    public String printByPlanetName(String planetName) {

        return universe.getPlanet(planetName).toString();

    }

    /**
     * Извежда данните за две планети
     *
     * @param planet1 име на първа планета
     * @param planet2 име на втора планета
     * @return Връща обект от типа String
     */
    public String printByPlanetsName(String planet1, String planet2) {

        return this.printByPlanetName(planet1) + this.printByPlanetName(planet2);

    }

    /**
     * Извежда данните за джедай
     *
     * @param name име на джедай
     * @return Връща онект от типа String
     */
    public String printByJediName(String name) {

        return getJedi(name).toString();

    }

    /**
     * Премахва всички данни
     */
    public void removeAll() {

        universe.removeAll();

    }
}
