package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;


public class GetStrongestJedi implements Command {
    private final JediManager jediManager;
    private final PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    public GetStrongestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != Commands.GET_STRONGEST_JEDI.getI()) {
            throw new CommandException("Usage: get_strongest_jedi <planet_name>");
        }
        String planet = args[1];

        if (planetsList.getPlanet(planet) != null) {
            if (jediManager.getStrongestJedi(planet) != null) {
                System.out.println(jediManager.getStrongestJedi(planet).toString());
            } else {
                throw new IOException("There are no jedis on the planet " + planet);
            }
        } else {
            throw new IOException("There is no planet with the name " + planet);
        }
    }
}
