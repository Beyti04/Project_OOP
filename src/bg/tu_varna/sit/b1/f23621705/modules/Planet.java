package bg.tu_varna.sit.b1.f23621705.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класът Planet представлява планета във вселена. Планетата има име 
 * и може да приюти списък с джедаи. Предоставя функционалност за управление 
 * на пребиваващите джедаи, включително добавяне, извличане или премахване на джедай, 
 * както и показване на цялата свързана информация за джедаите във форматиран вид.
 */
public class Planet {
    private final List<Jedi> jedis;
    private String name;

    public Planet(String name) {
        this.name=name;
        this.jedis = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    public void createJedi(Jedi jedi) {
        jedis.add(jedi);
    }

    /**
     * Взема джедай по име от планетата
     *
     * @param name име на джедай
     * @return връща обект от тип Jedi
     */
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

    /**
     * Премахва джедай
     *
     * @param name име на дйедай
     */
    public void removeJedi(String name) {
        jedis.remove(getJedi(name));
    }

    /**
     * Функция за извеждане на данните на една планета
     *
     * @return връща обект от тип String
     */
    @Override
    public String toString() {

        jedis.sort(Comparator.comparing(Jedi::getJediRank).thenComparing(Jedi::getName));
        String line = "=".repeat(30);

        final StringBuilder sb = new StringBuilder();

        sb.append("\nPlanet name: ").append(name).append("\n");
        sb.append(line);
        sb.append("\nJedi List: ");

        for (Jedi jedi : jedis) {

            String line1 = "-".repeat(30);
            sb.append('\n').append(line1);
            sb.append("\nName: ").append(jedi.getName());
            sb.append("\nRank: ").append(jedi.getJediRank().name().replace("_", " "));
            sb.append("\nAge: ").append(jedi.getAge());
            sb.append("\nLight saber colour: ").append(jedi.getLightsaberColour());
            sb.append("\nStrength: ").append(jedi.getStrength());
            sb.append('\n').append(line1);

        }

        return sb.toString();
    }

}
