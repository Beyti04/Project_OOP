package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.exceptions.DuplicateJediException;
import bg.tu_varna.sit.b1.f23621705.exceptions.NoJediException;

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

    public List<Jedi> getJedis() {
        return jedis;
    }

    @Override
    public void createJedi(Jedi jedi) throws DuplicateJediException {
        if (jedis.stream().anyMatch(jedi1 -> jedi1.getName().equals(jedi.getName()))) {
            throw new DuplicateJediException("Jedi already exists!");
        } else {
            jedis.add(jedi);
        }
    }

    public Jedi searchJedi(String jediName) throws NoJediException {
        if (jedis.isEmpty()) {
            throw new NoJediException("The jedi list is empty!");
        } else {
            for (Jedi jedi : jedis) {
                if (jedi.getName().equals(jediName)) {
                    return jedi;
                }
            }
            return null;
        }
    }
}
