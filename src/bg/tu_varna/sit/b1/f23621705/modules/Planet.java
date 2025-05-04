package bg.tu_varna.sit.b1.f23621705.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Planet {
    private final List<Jedi> jedis;
    private String name;

    public Planet(String name) {
        this.setName(name);
        this.jedis = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    public void createJedi(Jedi jedi) {
        jedis.add(jedi);
    }

    public Jedi getJedi(String name) {
        if (jedis.stream().anyMatch(jedi1 -> jedi1.getName().equals(name))) {
            for (Jedi jedi : jedis) {
                if (jedi.getName().equals(name)) {
                    return jedi;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        jedis.sort(Comparator.comparing(Jedi::getJediRank).thenComparing(Jedi::getName));
        String line = "=".repeat(20);
        final StringBuilder sb = new StringBuilder();
        sb.append("\nPlanet name: ").append(name).append("\n");
        sb.append(line);
        sb.append("\nJedi List: ");
        for (Jedi jedi : jedis) {
            sb.append("\n\nName: ").append(jedi.getName());
            sb.append("\nRank: ").append(jedi.getJediRank());
            sb.append("\nAge: ").append(jedi.getAge());
            sb.append("\nLight saber colour: ").append(jedi.getLightsaberColour());
            sb.append("\nStrength: ").append(jedi.getStrength());
        }
        return sb.toString();
    }
}
