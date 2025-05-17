package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;

/**
 * Класът Jedi представлява, воин със специални умения във вселената на Междузвездни войни.
 * Всеки Джедай има атрибути като планета на произход, име, ранг, възраст, цвят на светлинния меч
 * и ниво на сила. Предоставя функционалност за извличане и промяна на определени свойства.
 */
public class Jedi {
    private Planet planet;
    private String name;
    private JediRank jediRank;
    private int age;
    private final LightsaberColour lightsaberColour;
    private double strength;


    public Jedi(Planet planet, String name, JediRank jediRank, int age, LightsaberColour lightsaberColour, double strength) {
        this.planet = planet;
        this.name = name;
        this.jediRank = jediRank;
        this.age = age;
        this.lightsaberColour = lightsaberColour;
        this.strength = strength;

    }

    public String getName() {
        return name;
    }

    public JediRank getJediRank() {
        return jediRank;
    }

    public int getAge() {
        return age;
    }

    public LightsaberColour getLightsaberColour() {
        return lightsaberColour;
    }

    public Planet getPlanet() {
        return planet;
    }

    public double getStrength() {
        return strength;
    }

    public void setJediRank(JediRank jediRank) {
        this.jediRank = jediRank;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }



    /**
     * Функция за извеждане на данните на един джедай
     * @return връща обект от тип String
     */
    @Override
    public String toString() {
        String line = "=".repeat(30);
        String sb = '\n' + line + '\n' +
                "Name: " + name + '\n' +
                "Planet: " + planet.getName() + '\n' +
                "Rank: " + jediRank.name().replace("_"," ") + '\n' +
                "Age: " + age + '\n' +
                "Saber colour: " + lightsaberColour + '\n' +
                "Strength: " + strength + '\n' +
                line + '\n';
        return sb;
    }
}