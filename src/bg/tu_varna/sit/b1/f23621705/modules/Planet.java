package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.exceptions.DuplicateJediException;
import bg.tu_varna.sit.b1.f23621705.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Planet {
    private String name;
    private List<Jedi> jedis;

    public Planet(String name) throws InvalidDataException {
        this.setName(name);
        this.jedis = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    public void createJedi(Jedi jedi) throws DuplicateJediException {
        if (jedis.contains(jedi)) {
            throw new DuplicateJediException("There is already this jedi on this planet!");
        } else {
            jedis.add(jedi);
        }
    }

    public void setName(String name) throws InvalidDataException {
        if (name.isBlank()) {
            throw new InvalidDataException("There must be valid data for NAME!");
        } else {
            this.name = name;
        }
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
