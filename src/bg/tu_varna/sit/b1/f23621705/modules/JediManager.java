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
        if (JediList.getJedisInstance().getJedi(jedi.getName()) == null && PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).getJedi(jedi.getName()) == null) {
            JediList.getJedisInstance().createJedi(jedi);
            PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet()).createJedi(jedi);
        } else {
            System.out.println("Jedi already exists!");
        }
    }

    @Override
    public void removeJedi(String name, String planetName) {
        PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis().remove(JediList.getJedisInstance().getJedi(name));
        JediList.getJedisInstance().getJedis().remove(JediList.getJedisInstance().getJedi(name));
    }

    public Jedi getJedi(String name) {
        if (JediList.getJedisInstance().getJedi(name) != null && PlanetsList.getPlanetsInstance().getJedi(name) != null) {
            return JediList.getJedisInstance().getJedi(name);
        }
        return null;
    }

    public void promoteJedi(String name, Double multiplier) {
        JediRank[] ranks = JediRank.values();
        int current = JediList.getJedisInstance().getJedi(name).getJediRank().ordinal();
        JediList.getJedisInstance().getJedi(name).setJediRank(ranks[current + 1]);
        PlanetsList.getPlanetsInstance().getJedi(name).setJediRank(ranks[current + 1]);

        double strength = JediList.getJedisInstance().getJedi(name).getStrength();
        strength += (multiplier * strength);
        JediList.getJedisInstance().getJedi(name).setStrength(strength);
        PlanetsList.getPlanetsInstance().getJedi(name).setStrength(strength);

    }


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
        }

    }


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

    public Jedi getYoungestJedi(String planetName, JediRank rank) {
        List<Jedi> jedis = new ArrayList<>();
        for (Jedi jedi : PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis()) {
            if (jedi.getJediRank().equals(rank)) {
                jedis.add(jedi);
            }
        }
        return Collections.min(jedis, Comparator.comparing(Jedi::getAge).thenComparing(Jedi::getName));
    }

    public LightsaberColour mostUsedLightSaberColour(String planetName){
        List<Jedi> jedis=PlanetsList.getPlanetsInstance().getPlanet(planetName).getJedis();
        Set<LightsaberColour> colours=new HashSet<>();
        List<Jedi> allowedJedis=new ArrayList<>();
        Map<LightsaberColour,Integer> counter=new HashMap<>();
        LightsaberColour mostUsed=null;
        int maxCount=0;

        for(Jedi jedi:jedis){
            if(jedi.getJediRank().equals(JediRank.GRAND_MASTER)){
                colours.add(jedi.getLightsaberColour());
            }
        }

        for(Jedi jedi:jedis){
            if(colours.contains(jedi.getLightsaberColour())){
                allowedJedis.add(jedi);
            }
        }

        for(Jedi jedi: allowedJedis){
            counter.put(jedi.getLightsaberColour(),counter.getOrDefault(jedi.getLightsaberColour(),0)+1);
        }

        for(Map.Entry<LightsaberColour,Integer> entry:counter.entrySet()){
            if(entry.getValue()>maxCount){
                mostUsed=entry.getKey();
                maxCount=entry.getValue();
            }
        }

        return mostUsed;
    }

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

    public String printByPlanetName(String planetName) {
        Planet planet = PlanetsList.getPlanetsInstance().getPlanet(planetName);
        return planet.toString();
    }

    public String printByPlanetName(String planet1, String planet2) {
        return this.printByPlanetName(planet1) + this.printByPlanetName(planet2);
    }

    public String printByJediName(String name) {

        Jedi jedi = JediList.getJedisInstance().getJedi(name);
        return jedi.toString();

    }
}
