package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;
import java.util.List;

public class GetYoungestJedi implements Command {
    private final JediManager jediManager;

    List<JediRank> ranks = List.of(JediRank.values());

    public GetYoungestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != 3) {
            throw new CommandException("Usage: get_youngest_jedi <planet_name> <jedi_rank>");
        }
        String planet = args[1];

        if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
            String rank = args[2].toUpperCase();
            if (rank.isBlank() || ranks.stream().noneMatch(rank1 -> rank1.name().equals(rank))) {
                throw new IOException("There is no jedi with the rank of " + rank + " on the planet " + planet);
            } else {
                System.out.println(jediManager.getYoungestJedi(planet, ranks.stream().filter(rank1 -> rank1.name().equals(rank)).findFirst().get()));
            }
        } else {
            throw new IOException("There is no planet with the name " + planet);
        }
    }
}
