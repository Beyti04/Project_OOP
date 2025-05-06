package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.interfaces.JediCreator;

import java.util.ArrayList;
import java.util.List;

public class JediList implements JediCreator {
    private static JediList jedisInstance;
    private final List<Jedi> jedis = new ArrayList<>();

    /**
     * Функция за вземане на инстанцията на сингълтън списък с джедай
     *
     * @return Връща инстанцията на сингълтън класа
     */
    public static JediList getJedisInstance() {
        if (jedisInstance == null) {
            jedisInstance = new JediList();
        }
        return jedisInstance;
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    /**
     * Добавя джедай към списъка
     *
     * @param jedi параметър за джедай
     */
    @Override
    public void createJedi(Jedi jedi) {
        jedis.add(jedi);
    }

    /**
     * Премахва всички джедаи
     */
    public void removeAll() {
        jedis.removeAll(jedis);
    }

    /**
     * Взема даден джедай по име
     *
     * @param jediName параметър за име
     * @return Връща обект от тип Jedi ако съществува
     */
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
