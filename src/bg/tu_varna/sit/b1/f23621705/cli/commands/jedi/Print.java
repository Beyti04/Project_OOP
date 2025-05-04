package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;

public class Print implements Command {
    JediManager jediManager;

    public Print(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length == 2) {
            String name = args[1];
            if (jediManager.getJedi(name) != null) {
                System.out.println(jediManager.printByJediName(name));
            } else if (PlanetsList.getPlanetsInstance().getPlanet(name) != null) {
                System.out.println(jediManager.printByPlanetName(name));
            } else {
                throw new IOException("There is no data for this input!");
            }
        } else if (args.length == 3) {
            String planet1 = args[1];
            String planet2 = args[2];

            if (PlanetsList.getPlanetsInstance().getPlanet(planet1) != null && PlanetsList.getPlanetsInstance().getPlanet(planet2) != null) {
                System.out.println(jediManager.printByPlanetName(planet1, planet2));
            } else {
                throw new IOException("There is no data for this input!");
            }
        } else {
            throw new CommandException("Usage: print <jedi_name> / <planet_name>\n       print <planet_name1> <planet_name2>");
        }
    }
}
