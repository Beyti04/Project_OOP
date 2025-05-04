package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;
import java.util.List;

public class GetMostUsedLightsaberColour implements Command {
    private final JediManager jediManager;

    List<JediRank> ranks = List.of(JediRank.values());

    public GetMostUsedLightsaberColour(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length == 3) {
            String planet = args[1];
            if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
                String rank = args[2].toUpperCase();
                if (rank.isBlank() || ranks.stream().noneMatch(rank1 -> rank1.name().equals(rank))) {
                    throw new IOException("There is no jedi with the rank of " + rank + " on the planet " + planet);
                } else {
                    System.out.println("The most used light saber colour is: " + jediManager.mostUsedLightSaberColour(planet, ranks.stream().filter(rank1 -> rank1.name().equals(rank)).findFirst().get()));
                }
            } else {
                throw new IOException("There is no planet with the name " + planet);
            }
        } else if (args.length == 2) {
            String planet = args[1];
            if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
                if (PlanetsList.getPlanetsInstance().getPlanet(planet).getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(JediRank.GRAND_MASTER))) {
                    System.out.println("The most used light saber colour is: " + jediManager.mostUsedLightSaberColour(planet));
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
