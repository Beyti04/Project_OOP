package bg.tu_varna.sit.b1.f23621705.interfaces;

import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.exceptions.DuplicateJediException;
import bg.tu_varna.sit.b1.f23621705.exceptions.NoPlanetException;

public interface JediCreator {
    void createJedi(Jedi jedi) throws DuplicateJediException, NoPlanetException;
}
