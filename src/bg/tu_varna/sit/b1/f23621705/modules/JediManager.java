package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediRemover;

import java.util.*;

public class JediManager implements JediCreator, JediRemover {
    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    @Override
    public void createJedi(Jedi jedi) {
        if(JediList.getJedisInstance().getJedi(jedi.getName())==null&&PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).getJedi(jedi.getName())==null){
            JediList.getJedisInstance().createJedi(jedi);
            PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).createJedi(jedi);
        }else{
            System.out.println("Jedi already exists!");
        }
    }

    @Override
    public void removeJedi(String name, String planetName){
        if (JediList.getJedisInstance().getJedi(name) == null) {
            System.out.println("There is no jedi with this name!");
        } else {
            if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
                System.out.println("There is no planet with this name");
            } else {
                if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().contains(JediList.getJedisInstance().getJedi(name))) {
                    PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().remove(JediList.getJedisInstance().getJedi(name));
                    JediList.getJedisInstance().getJedis().remove(JediList.getJedisInstance().getJedi(name));
                } else {
                    System.out.println("There is no jedi with the name of " + name + " on planet " + planetName);
                }
            }
        }
    }

    public Jedi getJedi(String name){
        if(JediList.getJedisInstance().getJedi(name)!=null){
            return JediList.getJedisInstance().getJedi(name);
        }
        return null;
    }

    public void promoteJedi(String name, Double multiplier) {
        if (JediList.getJedisInstance().getJedi(name) == null) {
            System.out.println("There is no jedi with this name!");
        } else if (JediList.getJedisInstance().getJedi(name).getJediRank() == JediRank.GRAND_MASTER) {
            System.out.println("The highest rank has been reached!");
        } else {
            JediRank[] ranks = JediRank.values();
            int current = JediList.getJedisInstance().getJedi(name).getJediRank().ordinal();
            JediList.getJedisInstance().getJedi(name).setJediRank(ranks[current + 1]);
            PlanetsList.getPlanetsInstance().getJedi(name).setJediRank(ranks[current + 1]);

            if (multiplier != null && multiplier < 0) {
                double strength = JediList.getJedisInstance().getJedi(name).getStrength();
                strength += multiplier * strength;
                if (strength < MAX_STRENGTH) {
                    JediList.getJedisInstance().getJedi(name).setStrength(strength);
                    PlanetsList.getPlanetsInstance().getJedi(name).setStrength(strength);
                } else {
                    System.out.println("Strength too high!");
                }
            } else {
                System.out.println("There must be valid data for multiplier!");
            }
        }
    }

    public void demoteJedi(String name, Double multiplier) {
        if (JediList.getJedisInstance().getJedi(name) == null) {
            System.out.println("There is no jedi with this name!");
        } else if (JediList.getJedisInstance().getJedi(name).getJediRank() == JediRank.YOUNGLING) {
            System.out.println("The lowest rank has been reached!");
        } else {
            JediRank[] ranks = JediRank.values();
            int current = JediList.getJedisInstance().getJedi(name).getJediRank().ordinal();
            JediList.getJedisInstance().getJedi(name).setJediRank(ranks[current - 1]);
            PlanetsList.getPlanetsInstance().getJedi(name).setJediRank(ranks[current - 1]);

            if (multiplier != null && multiplier > 0) {
                double strength = JediList.getJedisInstance().getJedi(name).getStrength();
                strength -= multiplier * strength;
                if (strength > MIN_STRENGTH) {
                    JediList.getJedisInstance().getJedi(name).setStrength(strength);
                    PlanetsList.getPlanetsInstance().getJedi(name).setStrength(strength);
                } else {
                    JediList.getJedisInstance().getJedi(name).setStrength(MIN_STRENGTH);
                }
            } else {
                System.out.println("There must be valid data for multiplier!");
            }
        }
    }

    public Jedi getStrongestJedi(String planetName) {
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
            System.out.println("There is no such planet!");
        } else {
            if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().isEmpty()) {
                System.out.println("There are no jedis on this planet!");
            } else {
                List<Jedi> jedis = PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis();
                Jedi currentStrongest = jedis.getFirst();
                for (Jedi jedi : jedis) {
                    if (currentStrongest.getStrength() < jedi.getStrength()) {
                        currentStrongest = jedi;
                    }
                }
                return currentStrongest;
            }
        }
        return null;
    }

    public Jedi getYoungestJedi(String planetName, JediRank rank) {
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
            System.out.println("There is no planet with this name!");
        } else {
            if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().isEmpty()) {
                System.out.println("There is no jedis on this planet!");
            } else {
                if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {

                    List<Jedi> jedis = new ArrayList<>();
                    for (Jedi jedi : PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis()) {
                        if (jedi.getJediRank().equals(rank)) {
                            jedis.add(jedi);
                        }
                    }
                    return Collections.min(jedis, Comparator.comparing(Jedi::getAge).thenComparing(Jedi::getName));
                } else {
                    System.out.println("There are no jedis with this rank on this planet!");
                }
            }
        }
        return null;
    }

    public LightsaberColour mostUsedLightSaberColour(String planetName, JediRank rank) {
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
            System.out.println("There is no planet with this name!");
        } else {
            if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().isEmpty()) {
                System.out.println("There is no jedis on this planet!");
            } else {
                if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))) {

                    List<Jedi> jedis = new ArrayList<>();
                    for (Jedi jedi : PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis()) {
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
                    System.out.println("There are no jedis with this rank on this planet!");
                }
            }
        }
        return null;
    }

    public String printByPlanetName(String planetName) {
        if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
            return "There are no such planet!";
        } else {
            if (PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().isEmpty()) {
                return "There are no jedis on this planet!";
            } else {
                Planet planet = PlanetsList.getPlanetsInstance().getPlanet(planetName);
                return planet.toString();
            }
        }
    }

    public String printByPlanetName(String planet1, String planet2) {
        return this.printByPlanetName(planet1) + this.printByPlanetName(planet2);
    }

    public String printByJediName(String name) {
        if (JediList.getJedisInstance().getJedi(name) == null) {
            return "There is no jedi with this name!";
        } else {
            Jedi jedi = JediList.getJedisInstance().getJedi(name);
            return jedi.toString();
        }
    }
}
