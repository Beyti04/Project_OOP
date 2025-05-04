package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;


public class Jedi {
    private String planet;
    private String name;
    private JediRank jediRank;
    private int age;
    private LightsaberColour lightsaberColour;
    private double strength;


    public Jedi(String planet, String name, JediRank jediRank, int age, LightsaberColour lightsaberColour, double strength) {
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

    public void setName(String name) {
        this.name = name;
    }

    public JediRank getJediRank() {
        return jediRank;
    }

    public void setJediRank(JediRank jediRank) {
        this.jediRank = jediRank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LightsaberColour getLightsaberColour() {
        return lightsaberColour;
    }

    public void setLightsaberColour(LightsaberColour lightsaberColour) {
        this.lightsaberColour = lightsaberColour;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        String sb = "\nName: " + name +
                "\nPlanet: " + planet +
                "\nRank: " + jediRank +
                "\nAge: " + age +
                "\nLight saber colour: " + lightsaberColour +
                "\nStrength: " + strength;
        return sb;
    }
}