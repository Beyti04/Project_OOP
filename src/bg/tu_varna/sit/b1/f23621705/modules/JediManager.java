package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.exceptions.*;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediRemover;

import java.util.*;

public class JediManager implements JediCreator, JediRemover {
    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 0;

    @Override
    public void createJedi(Jedi jedi) throws DuplicateJediException, NoPlanetException {
        JediList.getJedisInstance().createJedi(jedi);
        PlanetsList.getPlanetsInstance().searchPlanet(jedi.getPlanet()).createJedi(jedi);
    }

    @Override
    public void removeJedi(String name, String planetName) throws NoPlanetException, NoJediException {
        if (JediList.getJedisInstance().searchJedi(name) == null) {
            throw new NoJediException("There is no jedi with this name!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName) == null) {
                throw new NoPlanetException("There is no planet with this name");
            } else {
                if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().contains(JediList.getJedisInstance().searchJedi(name))) {
                    PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().remove(JediList.getJedisInstance().searchJedi(name));
                    JediList.getJedisInstance().getJedis().remove(JediList.getJedisInstance().searchJedi(name));
                } else {
                    throw new NoJediException("There is no jedi with the name of " + name + " on planet " + planetName);
                }
            }
        }
    }

    void promoteJedi(String name, Double multiplier) throws MaxRankException, InvalidDataException, NoJediException, NoPlanetException {
        if (JediList.getJedisInstance().searchJedi(name) == null) {
            throw new NoJediException("There is no jedi with this name!");
        } else if (JediList.getJedisInstance().searchJedi(name).getJediRank() == JediRank.GRAND_MASTER) {
            throw new MaxRankException("The highest rank has been reached!");
        } else {
            JediRank[] ranks = JediRank.values();
            int current = JediList.getJedisInstance().searchJedi(name).getJediRank().ordinal();
            JediList.getJedisInstance().searchJedi(name).setJediRank(ranks[current + 1]);
            PlanetsList.getPlanetsInstance().searchJedi(name).setJediRank(ranks[current + 1]);

            if (multiplier != null && multiplier < 0) {
                double strength = JediList.getJedisInstance().searchJedi(name).getStrength();
                strength += multiplier * strength;
                if (strength < MAX_STRENGTH) {
                    JediList.getJedisInstance().searchJedi(name).setStrength(strength);
                    PlanetsList.getPlanetsInstance().searchJedi(name).setStrength(strength);
                } else {
                    throw new InvalidDataException("Strength too high!");
                }
            } else {
                throw new InvalidDataException("There must be valid data for multiplier!");
            }
        }
    }

    void demoteJedi(String name, Double multiplier) throws MinRankException, InvalidDataException, NoJediException, NoPlanetException {
        if (JediList.getJedisInstance().searchJedi(name) == null) {
            throw new NoJediException("There is no jedi with this name!");
        } else if (JediList.getJedisInstance().searchJedi(name).getJediRank() == JediRank.YOUNGLING) {
            throw new MinRankException("The lowest rank has been reached!");
        } else {
            JediRank[] ranks = JediRank.values();
            int current = JediList.getJedisInstance().searchJedi(name).getJediRank().ordinal();
            JediList.getJedisInstance().searchJedi(name).setJediRank(ranks[current - 1]);
            PlanetsList.getPlanetsInstance().searchJedi(name).setJediRank(ranks[current - 1]);

            if (multiplier != null && multiplier > 0) {
                double strength = JediList.getJedisInstance().searchJedi(name).getStrength();
                strength -= multiplier * strength;
                if (strength > MIN_STRENGTH) {
                    JediList.getJedisInstance().searchJedi(name).setStrength(strength);
                    PlanetsList.getPlanetsInstance().searchJedi(name).setStrength(strength);
                } else {
                    throw new InvalidDataException("Strength too low!");
                }
            } else {
                throw new InvalidDataException("There must be valid data for multiplier!");
            }
        }
    }

    public Jedi getStrongestJedi(String planetName) throws NoJediException, NoPlanetException {
        if (PlanetsList.getPlanetsInstance().searchPlanet(planetName) == null) {
            throw new NoPlanetException("There is no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().isEmpty()) {
                throw new NoJediException("There are no jedis on this planet!");
            } else {
                List<Jedi> jedis = PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis();
                Jedi currentStrongest = jedis.get(0);
                for (Jedi jedi : jedis) {
                    if (currentStrongest.getStrength() < jedi.getStrength()) {
                        currentStrongest = jedi;
                    }
                }
                return currentStrongest;
            }
        }
    }

    public Jedi getYoungestJedi(String planetName, JediRank rank) throws NoJediException, NoPlanetException {
        if (PlanetsList.getPlanetsInstance().searchPlanet(planetName) == null) {
            throw new NoPlanetException("There is no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().isEmpty()) {
                throw new NoJediException("There is no jedis on this planet");
            } else {
                if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {

                    List<Jedi> jedis = new ArrayList<>();
                    for (Jedi jedi : PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis()) {
                        if (jedi.getJediRank().equals(rank)) {
                            jedis.add(jedi);
                        }
                    }
                    return Collections.min(jedis, Comparator.comparing(Jedi::getAge).thenComparing(Jedi::getName));
                } else {
                    throw new NoJediException("There are no jedis with this rank on this planet!");
                }
            }
        }
    }

    public LightsaberColour mostUsedLightSaberColour(String planetName, JediRank rank) throws NoPlanetException, NoJediException {
        if (PlanetsList.getPlanetsInstance().searchPlanet(planetName) == null) {
            throw new NoPlanetException("There is no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().isEmpty()) {
                throw new NoJediException("There is no jedis on this planet");
            } else {
                if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {

                    List<Jedi> jedis = new ArrayList<>();
                    for (Jedi jedi : PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis()) {
                        if (jedi.getJediRank().equals(rank)) {
                            jedis.add(jedi);
                        }
                    }

                    Map<LightsaberColour, Integer> counter = new HashMap<>();
                    for (Jedi jedi : jedis) {
                        counter.put(jedi.getLightsaberColour(), counter.getOrDefault(jedi.getLightsaberColour(), 0) + 1);
                    }

                    LightsaberColour mostUsed = null;
                    int maxCount = 0;
                    for (Map.Entry<LightsaberColour, Integer> entry : counter.entrySet()) {
                        if (entry.getValue() > maxCount) {
                            mostUsed = entry.getKey();
                            maxCount = entry.getValue();
                        }
                    }

                    return mostUsed;
                } else {
                    throw new NoJediException("There are no jedis with this rank on this planet!");
                }
            }
        }
    }

    public String printByPlanetName(String planetName) throws NoPlanetException, NoJediException {
        if (PlanetsList.getPlanetsInstance().searchPlanet(planetName) == null) {
            throw new NoPlanetException("There are no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().isEmpty()) {
                throw new NoJediException("There are no jedis on this planet!");
            } else {
                Planet planet = PlanetsList.getPlanetsInstance().searchPlanet(planetName);
                return planet.toString();
            }
        }
    }

    public String printByPlanetName(String planet1, String planet2) throws NoPlanetException, NoJediException {
        return this.printByPlanetName(planet1) + this.printByPlanetName(planet2);
    }

    public String printByJediName(String name) throws NoJediException {
        if (JediList.getJedisInstance().searchJedi(name) == null) {
            throw new NoJediException("There is no such jedi!");
        } else {
            Jedi jedi = JediList.getJedisInstance().searchJedi(name);
            return jedi.toString();
        }
    }
}
