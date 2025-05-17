package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

/**
 * GetStrongestJedi класът имплементира Command интерфейса за изпълнение
 * на специфична команда за намиране на най-силния Джедай на дадена планета.
 * Взаимодейства с JediManager и Universe класовете за извличане
 * и обработка на необходимите данни.
 */
public class GetStrongestJedi implements Command {
    private final JediManager jediManager;
    private final Universe universe = Universe.getUniverseInstance();

    public GetStrongestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length != Commands.GET_STRONGEST_JEDI.getI()) {

            throw new CommandException("Usage: get_strongest_jedi <planet_name>");

        }

        Planet planet= universe.getPlanet(args[1]);

        if (planet != null) {

            if (planet.getJedis() != null) {

                System.out.println(jediManager.getStrongestJedi(args[1]).toString());

            } else {

                throw new IOException("There are no jedis on the planet " + args[1]);

            }

        } else {

            throw new IOException("There is no planet with the name " + args[1]);

        }

    }

}
