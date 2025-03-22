package bg.tu_varna.sit.b1.f23621705;

import java.util.ArrayList;
import java.util.List;

public class JediList implements JediCreator {
    private static JediList jedisInstance;
    private List<Jedi> jedis;

    public static JediList getJedisInstance() {
        if (jedisInstance == null) {
            jedisInstance = new JediList();
        }
        return jedisInstance;
    }

    @Override
    public void addJedi(Jedi jedi) throws DuplicateJediException {
        if (jedis.stream().anyMatch(jedi1 -> jedi1.getName().equals(jedi.getName()))) {
            throw new DuplicateJediException("Jedi already exists!");
        } else {
            jedis.add(jedi);
        }
    }
}
