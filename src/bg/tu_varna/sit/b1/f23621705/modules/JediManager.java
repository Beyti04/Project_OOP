package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediRemover;

import java.util.*;

/**
 * Класът JediManager отговаря за операциите свързани с джедаите, като създаване,
 * премахване, повишаване, понижаване и извличане на информация за джедаите във Вселената.
 * Имплементира интерфейсите JediCreator и JediRemover
 */
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

        Jedi jedi=getJedi(name);

        jedi.setJediRank(jedi.getJediRank().next());
        double strength = jedi.getStrength();

        strength += (multiplier * strength);

        if (strength <= MAX_STRENGTH) {

            jedi.setStrength(strength);

        } else {

            jedi.setStrength(MAX_STRENGTH);

        }
    }

    /**
     * Понижава ранга и силата на джедай
     *
     * @param name       име на джедай
     * @param multiplier множител за намаляване на силата
     */
    public void demoteJedi(String name, Double multiplier) {

        Jedi jedi=getJedi(name);

        jedi.setJediRank(jedi.getJediRank().prev());
        double strength = jedi.getStrength();

        strength -= (multiplier * strength);

        if (strength >= MIN_STRENGTH) {

            jedi.setStrength(strength);

        } else {

            jedi.setStrength(MIN_STRENGTH);

        }
    }

    /**
     * Взема на силния джедай от дадена планета
     *
     * @param planetName име на планета
     * @return Връща най-силния джедай от планетата ако има джедаи на планетата
     */
    public Jedi getStrongestJedi(String planetName) {

        if (!universe.getPlanet(planetName).getJedis().isEmpty()) {

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

            List<Jedi> jedis = new ArrayList<>();

            for (Jedi jedi : universe.getPlanet(planetName).getJedis()) {

                if (jedi.getJediRank().equals(rank)) {

                    jedis.add(jedi);

                }

            }

            if(jedis.isEmpty()){
                return null;
            }

            return Collections.min(jedis, Comparator.comparing(Jedi::getAge).thenComparing(Jedi::getName));
    }

    /**
     * Взема най-използвания цвят за светлинен меч на планетата
     *
     * @param planetName име на планета
     * @return Връща обект от тип LightsaberColour
     */
    public LightsaberColour mostUsedLightSaberColour(String planetName, JediRank filterRank) {
        List<Jedi> jedis = universe.getPlanet(planetName).getJedis();
        Set<LightsaberColour> validColours = new HashSet<>();
        List<Jedi> allowedJedis = new ArrayList<>();
        Map<LightsaberColour, Integer> counter = new HashMap<>();

        LightsaberColour mostUsed = null;
        int maxCount = 0;

        if (filterRank == null) {
            for (Jedi jedi : jedis) {
                if (jedi.getJediRank().equals(JediRank.GRAND_MASTER)) {
                    validColours.add(jedi.getLightsaberColour());
                }
            }
        }

        for (Jedi jedi : jedis) {
            boolean isAllowed;

            if (filterRank == null) {
                isAllowed = validColours.contains(jedi.getLightsaberColour());
            } else {
                isAllowed = jedi.getJediRank().equals(filterRank);
            }

            if (isAllowed) {
                allowedJedis.add(jedi);
            }
        }

        for (Jedi jedi : allowedJedis) {
            LightsaberColour colour = jedi.getLightsaberColour();
            counter.put(colour, counter.getOrDefault(colour, 0) + 1);
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
