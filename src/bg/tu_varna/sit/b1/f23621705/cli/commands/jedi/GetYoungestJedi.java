package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;
import java.util.List;

public class GetYoungestJedi implements Command {
    private final JediManager jediManager;
    private final PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    public GetYoungestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != Commands.GET_YOUNGEST_JEDI.getI()) {
            throw new CommandException("Usage: get_youngest_jedi <planet_name> <jedi_rank>");
        }
        String planet = args[1];

        if (planetsList.getPlanet(planet) != null) {
            String rank = args[2].toUpperCase();
            if (rank.isBlank() || jediManager.getYoungestJedi(planet, JediRank.valueOf(rank)) == null) {
                throw new IOException("There is no jedi with the rank of " + rank + " on the planet " + planet);
            } else {
                System.out.println(jediManager.getYoungestJedi(planet, JediRank.valueOf(rank)));
            }
        } else {
            throw new IOException("There is no planet with the name " + planet);
        }
    }
}
