package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;

public class GetMostUsedLightsaberColour implements Command {
    private final JediManager jediManager;
    private final PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    public GetMostUsedLightsaberColour(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length == Commands.GET_MOST_USED_SABER_COLOUR.getI()) {
            String planet = args[1];
            if (planetsList.getPlanet(planet) != null) {
                String rank=args[2];
                if (args[2].isBlank() || !JediRank.exists(rank)) {
                    throw new IOException("There is no jedi with the rank of " + rank + " on the planet " + planet);
                } else {
                    System.out.println("The most used light saber colour on the planet " + planet +
                            "\nby the jedis with rank " + rank + " is " + jediManager.mostUsedLightSaberColour(planet, JediRank.valueOf("rank")));
                }
            } else {
                throw new IOException("There is no planet with the name " + planet);
            }
        } else if (args.length == Commands.GET_MOST_USED_SABER_COLOUR.getJ()) {
            String planet = args[1];
            if (planetsList.getPlanet(planet) != null) {
                if (planetsList.getPlanet(planet).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(JediRank.GRAND_MASTER))) {
                    System.out.println("The most used light saber colour on the planet " + planet + " is " + jediManager.mostUsedLightSaberColour(planet));
                } else {
                    throw new IOException("There are no jedis with the rank GRAND_MASTER on the planet " + planet);
                }
            } else {
                throw new IOException("There is no planet with the name " + planet);
            }
        } else {
            throw new CommandException("Usage: get_most_used_saber_colour <planet_name> /\n       get_most_used_saber_colour <planet_name> <rank>");
        }
    }
}
