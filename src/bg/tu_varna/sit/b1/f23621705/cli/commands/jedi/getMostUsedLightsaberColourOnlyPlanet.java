package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.*;

public class getMostUsedLightsaberColourOnlyPlanet implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    List<JediRank> ranks= List.of(JediRank.values());

    public getMostUsedLightsaberColourOnlyPlanet(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {


        String planet=scanner.nextLine();

        if(PlanetsList.getPlanetsInstance().getPlanet(planet)!=null){
            if(PlanetsList.getPlanetsInstance().getPlanet(planet).getJedis().stream().anyMatch(jedi1->jedi1.getJediRank().equals(JediRank.GRAND_MASTER))){
                System.out.println(jediManager.mostUsedLightSaberColour(planet));
            }else{
                System.out.println("There are no jedis with the rank GRAND_MASTER on the planet "+planet);
            }
        }else{
            System.out.println("There is no planet with the name "+planet);
        }
    }
}
