package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;


public class Jedi {
    private String name;
    private JediRank jediRank;
    private int age;
    private LightsaberColour lightsaberColour;
    private double strength;
    private String planet;

    public Jedi(String name, JediRank jediRank, int age, LightsaberColour lightsaberColour, double strength, String planet) {
        this.name = name;
        this.jediRank = jediRank;
        this.age = age;
        this.lightsaberColour = lightsaberColour;
        this.strength = strength;
        this.planet = planet;
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

    public double getStrength() {
        return strength;
    }

    public String getPlanet() {
        return planet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJediRank(JediRank jediRank) {
        this.jediRank = jediRank;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLightsaberColour(LightsaberColour lightsaberColour) {
        this.lightsaberColour = lightsaberColour;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(name);
        sb.append("\nRank: ").append(jediRank);
        sb.append("\nAge: ").append(age);
        sb.append("\nLight saber colour: ").append(lightsaberColour);
        sb.append("\nStrength: ").append(strength);
        sb.append("\nPlanet: ").append(planet);
        return sb.toString();
    }
}