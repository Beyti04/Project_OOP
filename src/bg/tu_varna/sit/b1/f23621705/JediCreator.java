package bg.tu_varna.sit.b1.f23621705;

public interface JediCreator {
    void addJedi(Jedi jedi) throws DuplicateJediException, NoPlanetException;
}
