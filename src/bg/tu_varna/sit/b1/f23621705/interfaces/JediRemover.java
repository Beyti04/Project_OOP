package bg.tu_varna.sit.b1.f23621705.interfaces;

import bg.tu_varna.sit.b1.f23621705.exceptions.NoJediException;
import bg.tu_varna.sit.b1.f23621705.exceptions.NoPlanetException;

public interface JediRemover {
    void removeJedi(String name, String planetName) throws NoPlanetException, NoJediException;
}
