package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

public class RemoveJedi implements Command {
    private final JediManager jediManager;
    private final Universe universe = Universe.getUniverseInstance();

    public RemoveJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length != Commands.REMOVE_JEDI.getI()) {

            throw new CommandException("Usage: remove_jedi <jedi_first_name> <jedi_last_name> <planet_name>");

        }

        Planet planet= universe.getPlanet(args[3]);

        if (planet != null) {

            String name=args[1]+" "+args[2];

            if (planet.getJedi(name) != null) {

                jediManager.removeJedi(name, args[3]);
                System.out.println("Jedi " + name + " has been removed from planet " + planet);

            } else {

                throw new IOException("There is no jedi with the name: " + name + " on planet: " + planet);

            }
        } else {

            throw new IOException("There is no planet with the name: " + args[3]);

        }

    }

}
