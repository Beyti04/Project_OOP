package bg.tu_varna.sit.b1.f23621705;

public class Jedi implements JediCreator{
    private final double MAX_STRENGTH = 2;

    private String name;
    private JediRank jediRank;
    private Integer age;
    private LightsaberColour lightsaberColour;
    private Double strength;
    private String planet;

    public Jedi(String name, JediRank jediRank, int age, LightsaberColour lightsaberColour, double strength, String planet) throws InvalidDataException, NoPlanetException {
        this.setName(name);
        this.setJediRank(jediRank);
        this.setAge(age);
        this.setLightsaberColour(lightsaberColour);
        this.setStrength(strength);
        this.setPlanet(planet);
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

    public void setName(String name) throws InvalidDataException {
        if (name.isBlank()) {
            throw new InvalidDataException("There must be valid data for NAME!");
        } else {
            this.name = name;
        }
    }

    public void setJediRank(JediRank jediRank) throws InvalidDataException {
        if (jediRank == null) {
            throw new InvalidDataException("There must be valid data for RANK!");
        } else {
            this.jediRank = jediRank;
        }
    }

    public void setAge(Integer age) throws InvalidDataException {
        if (age == null) {
            throw new InvalidDataException("There must be valid data for AGE!");
        } else {
            this.age = age;
        }
    }

    public void setLightsaberColour(LightsaberColour lightsaberColour) throws InvalidDataException {
        if (lightsaberColour == null) {
            throw new InvalidDataException("There must be valid data for COLOUR!");
        } else {
            this.lightsaberColour = lightsaberColour;
        }
    }

    public void setStrength(Double strength) throws InvalidDataException {
        if (strength == null) {
            throw new InvalidDataException("There must be valid data for STRENGTH!");
        } else {
            this.strength = strength;
        }
    }

    public void setPlanet(String planet) throws InvalidDataException, NoPlanetException {
        if (PlanetsList.getPlanetsInstance().searchPlanet(planet) == null) {
            throw new InvalidDataException("There must be valid PLANET NAME");
        } else {
            this.planet = planet;
        }
    }

    @Override
    public void addJedi(Jedi jedi) throws DuplicateJediException, NoPlanetException {
        PlanetsList.getPlanetsInstance().searchPlanet(getPlanet()).addJedi(this);
        JediList.getJedisInstance().addJedi(this);
    }
}
