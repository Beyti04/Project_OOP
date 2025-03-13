package bg.tu_varna.sit.b1.f23621705;

public class Planet {
    private String name;
    private Jedi[] jedis;

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Jedi[] getJedis() {
        return jedis;
    }
}
