package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

/**
 * Класът AddPlanet имплементира интерфейса Command и е отговорен 
 * за добавяне на нова планета към Universe. Проверява дали планетата вече 
 * съществува във Universe и се справя със създаването или обработката на изключения съответно.
 */
public class AddPlanet implements Command {
    private final Universe universe = Universe.getUniverseInstance();

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length != Commands.ADD_PLANET.getI()) {

            throw new CommandException("Usage: add_planet <planet_name>");

        }

        Planet planet= universe.getPlanet(args[1]);

        if (planet == null) {

            universe.createPlanet(new Planet(args[1]));
            System.out.println("Planet " + args[1] + " has been added to the universe!");

        } else {

            throw new IOException("Planet with the name " + args[1] + " already exists!");

        }

    }

}
