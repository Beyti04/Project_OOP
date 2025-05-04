package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

public class AddPlanet implements Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 2){
            String planetName = args[1];

            if (PlanetsList.getPlanetsInstance().getPlanet(planetName) == null) {
                PlanetsList.getPlanetsInstance().createPlanet(new Planet(planetName));
            } else {
                System.out.println("Planet with the name " + planetName + " already exists!");
            }
        } else {
            throw new CommandException("Usage: add_planet <planet_name>");
        }
    }
}
