package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.*;

import java.util.List;

public class JediManager implements JediCreator,JediRemover {
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

    public Jedi getYoungestJedi(String planetName) throws NoJediException, NoPlanetException {
        if (PlanetsList.getPlanetsInstance().searchJedi(planetName) == null) {
            throw new NoPlanetException("There is no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis().isEmpty()) {
                throw new NoJediException("There is no jedis on this planet");
            } else {
                List<Jedi> jedis = PlanetsList.getPlanetsInstance().searchPlanet(planetName).getJedis();
                Jedi currentYoungest = jedis.get(0);
                for (Jedi jedi : jedis) {
                    if (currentYoungest.getAge() > jedi.getAge()) {
                        currentYoungest = jedi;
                    }
                }
                return currentYoungest;
            }
        }
    }
}
