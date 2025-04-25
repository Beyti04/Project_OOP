package bg.tu_varna.sit.b1.f23621705.modules;
import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;

import java.util.ArrayList;
import java.util.List;

public class JediList implements JediCreator {
    private static JediList jedisInstance;
    private final List<Jedi> jedis=new ArrayList<>();

    public static JediList getJedisInstance() {
        if (jedisInstance == null) {
            jedisInstance = new JediList();
        }
        return jedisInstance;
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    @Override
    public void createJedi(Jedi jedi) {
        jedis.add(jedi);
    }

    public Jedi getJedi(String jediName) {
        if (jedis.isEmpty()) {
            return null;
        } else {
            for (Jedi jedi : jedis) {
                if (jedi.getName().equals(jediName)) {
                    return jedi;
                }
            }
        }
        return null;
    }
}
