package bg.tu_varna.sit.b1.f23621705;

public interface JediCreator {
    void createJedi(Jedi jedi) throws DuplicateJediException, NoPlanetException;
}
