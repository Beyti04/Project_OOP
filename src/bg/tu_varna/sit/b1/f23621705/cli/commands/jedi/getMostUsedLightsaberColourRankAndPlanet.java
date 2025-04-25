package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.List;
import java.util.Scanner;

public class getMostUsedLightsaberColourRankAndPlanet implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    List<JediRank> ranks= List.of(JediRank.values());

    public getMostUsedLightsaberColourRankAndPlanet(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        System.out.println("Enter planet name: ");
        String planet=scanner.nextLine();

        if(PlanetsList.getPlanetsInstance().getPlanet(planet)!=null){
            String rank=scanner.nextLine().toUpperCase();
            if(rank.isBlank()||ranks.stream().noneMatch(rank1->rank1.name().equals(rank))){
                System.out.println("There is no jedi with the rank of "+rank+" on the planet "+planet);
            }else{
                System.out.println(jediManager.mostUsedLightSaberColour(planet,ranks.stream().filter(rank1->rank1.name().equals(rank)).findFirst().get()));
            }
        }else{
            System.out.println("There is no planet with the name "+planet);
        }
    }
}
