package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediRemover;

import java.util.*;

public class JediManager implements JediCreator, JediRemover {
    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    /**
     * Създава нов джедай в двата и ги записва в съответните списъци
     *
     * @param jedi джедай, когото добавяме
     */
    @Override
    public void createJedi(Jedi jedi) {
        if (JediList.getJedisInstance().getJedi(jedi.getName()) == null && PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).getJedi(jedi.getName()) == null) {
            JediList.getJedisInstance().createJedi(jedi);
            PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).createJedi(jedi);
        } else {
            System.out.println("Jedi already exists!");
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
        PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().remove(JediList.getJedisInstance().getJedi(name));
        JediList.getJedisInstance().getJedis().remove(JediList.getJedisInstance().getJedi(name));
    }

    /**
     * Взема джедай ако съществува
     *
     * @param name име на джедай
     * @return Връща обект от типа Jedi ако съществува
     */
    public Jedi getJedi(String name) {
        if (JediList.getJedisInstance().getJedi(name) != null && PlanetsList.getPlanetsInstance().getJedi(name) != null) {
            return JediList.getJedisInstance().getJedi(name);
        } else {
            return null;
        }
    }

    public List<Jedi> getJedis() {
        return JediList.getJedisInstance().getJedis();
    }

    /**
     * Повишава ранга и силата на джедай
     *
     * @param name       име на джедай
     * @param multiplier множител за увеличаване на силата
     */
    public void promoteJedi(String name, Double multiplier) {
        JediRank[] ranks = JediRank.values();
        int current = JediList.getJedisInstance().getJedi(name).getJediRank().ordinal();

        JediList.getJedisInstance().getJedi(name).setJediRank(ranks[current + 1]);
        PlanetsList.getPlanetsInstance().getJedi(name).setJediRank(ranks[current + 1]);

        double strength = JediList.getJedisInstance().getJedi(name).getStrength();

        strength += (multiplier * strength);
        if (strength <= 2) {
            JediList.getJedisInstance().getJedi(name).setStrength(strength);
            PlanetsList.getPlanetsInstance().getJedi(name).setStrength(strength);
        } else {
            JediList.getJedisInstance().getJedi(name).setStrength(MAX_STRENGTH);
            PlanetsList.getPlanetsInstance().getJedi(name).setStrength(MAX_STRENGTH);
        }


    }

    /**
     * Понижава ранга и силата на джедай
     *
     * @param name       име на джедай
     * @param multiplier множител за намаляване на силата
     */
    public void demoteJedi(String name, Double multiplier) {
        JediRank[] ranks = JediRank.values();
        int current = JediList.getJedisInstance().getJedi(name).getJediRank().ordinal();
        JediList.getJedisInstance().getJedi(name).setJediRank(ranks[current - 1]);
        PlanetsList.getPlanetsInstance().getJedi(name).setJediRank(ranks[current - 1]);

        double strength = JediList.getJedisInstance().getJedi(name).getStrength();
        strength -= multiplier * strength;
        if (strength > MIN_STRENGTH) {
            JediList.getJedisInstance().getJedi(name).setStrength(strength);
            PlanetsList.getPlanetsInstance().getJedi(name).setStrength(strength);
        } else {
            JediList.getJedisInstance().getJedi(name).setStrength(MIN_STRENGTH);
            PlanetsList.getPlanetsInstance().getJedi(name).setStrength(MIN_STRENGTH);
        }
    }

    /**
     * Взема на силния джедай от дадена планета
     *
     * @param planetName име на планета
     * @return Връща най-силния джедай от планетата ако има джедаи на планетата
     */
    public Jedi getStrongestJedi(String planetName) {
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis() != null) {
            List<Jedi> jedis = PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis();
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
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {
            List<Jedi> jedis = new ArrayList<>();
            for (Jedi jedi : PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis()) {
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
     * @return Връща обект от типа LightsaberColour
     */
    public LightsaberColour mostUsedLightSaberColour(String planetName) {
        List<Jedi> jedis = PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis();
        Set<LightsaberColour> colours = new HashSet<>();
        List<Jedi> allowedJedis = new ArrayList<>();
        Map<LightsaberColour, Integer> counter = new HashMap<>();
        LightsaberColour mostUsed = null;
        int maxCount = 0;

        for (Jedi jedi : jedis) {
            if (jedi.getJediRank().equals(JediRank.GRAND_MASTER)) {
                colours.add(jedi.getLightsaberColour());
            }
        }

        for (Jedi jedi : jedis) {
            if (colours.contains(jedi.getLightsaberColour())) {
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
        for (Jedi jedi : PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis()) {
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
        Planet planet = PlanetsList.getPlanetsInstance().getPlanet(planetName);
        return planet.toString();
    }

    /**
     * Извежда данните за две планети
     *
     * @param planet1 име на първа планета
     * @param planet2 име на втора планета
     * @return Връща обект от типа String
     */
    public String printByPlanetName(String planet1, String planet2) {
        return this.printByPlanetName(planet1) + this.printByPlanetName(planet2);
    }

    /**
     * Извежда данните за джедай
     *
     * @param name име на джедай
     * @return Връща онект от типа String
     */
    public String printByJediName(String name) {

        Jedi jedi = JediList.getJedisInstance().getJedi(name);
        return jedi.toString();

    }

    /**
     * Премахва всички данни
     */
    public void removeAll() {
        JediList.getJedisInstance().removeAll();
        PlanetsList.getPlanetsInstance().removeAll();
    }
}
