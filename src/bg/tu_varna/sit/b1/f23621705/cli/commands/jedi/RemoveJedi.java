package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;

public class RemoveJedi implements Command {
    private final JediManager jediManager;

    public RemoveJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != 3) {
            throw new CommandException("Usage: remove_jedi <jedi_name> <planet_name>");
        }

        String name = args[1];
        String planet = args[2];

        if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
            if (jediManager.getJedi(name) != null && PlanetsList.getPlanetsInstance().getPlanet(planet).getJedi(name) != null) {
                jediManager.removeJedi(name, planet);
                System.out.println("Jedi " + name + " has been removed from planet " + planet);
            } else {
                throw new IOException("There is no jedi with the name: " + name + " on planet: " + planet);
            }
        } else {
            throw new IOException("There is no planet with the name: " + planet);
        }
    }
}
