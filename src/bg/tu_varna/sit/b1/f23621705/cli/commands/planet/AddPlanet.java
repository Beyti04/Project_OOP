package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

public class AddPlanet implements Command {
    private final PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != Commands.ADD_PLANET.getI()) {
            throw new CommandException("Usage: add_planet <planet_name>");
        }
        String planetName = args[1];

        if (planetsList.getPlanet(planetName) == null) {
            planetsList.createPlanet(new Planet(planetName));
        } else {
            System.out.println("Planet with the name " + planetName + " already exists!");
        }
    }
}
